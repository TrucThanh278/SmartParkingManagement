package com.ou.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.dto.request.ChangePasswordRequest;
import com.ou.dto.request.DTOUserRequest;
import com.ou.dto.request.DTOUserUpdateRequest;
import com.ou.dto.response.DTOUserResponse;
import com.ou.mapper.UserMapper;
import com.ou.pojo.Role;
import com.ou.pojo.User;
import com.ou.pojo.VerificationToken;
import com.ou.repository.RoleRepository;
import com.ou.repository.UserRepository;
import com.ou.repository.VerificationTokenRepository;
import com.ou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private VerificationTokenRepository tokenRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoleId().getName()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public boolean authUser(String username, String password) {
        User user = getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return passEncoder.matches(password, user.getPassword());
    }

    @Override
    public DTOUserResponse getDTOUserByUsername(String username) {
        User user = getUserByUsername(username);
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public User addUser(DTOUserRequest dtoUserRequest, MultipartFile avatar) {
        try {

            User user = userMapper.toUser(dtoUserRequest);
            user.setPassword(passEncoder.encode(user.getPassword()));

            // Set default role
            Role defaultRole;
            try {
                defaultRole = roleRepo.findById(1L);
            } catch (Exception e) {
                throw new RuntimeException("Default role not found with ID: 1", e);
            }
            user.setRoleId(defaultRole);

            // Upload avatar
            if (!avatar.isEmpty()) {
                try {
                    Map<?, ?> uploadResult = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap());
                    String avatarUrl = (String) uploadResult.get("url");
                    user.setAvatar(avatarUrl);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to upload avatar", e);
                }
            }

            // Save user
            user = userRepo.save(user);

            // Create verification token
            String token = UUID.randomUUID().toString();

            createVerificationToken(user, token);

            // Send verification email
            sendVerificationEmail(user.getEmail(), token);

            return user;
        } catch (Exception e) {
            throw new RuntimeException("Failed to add user", e);
        }
    }

    private void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);

        // Set expiry date to 24 hours from now
        verificationToken.setExpiryDate(LocalDateTime.now().plusMinutes(VerificationToken.getEXPIRATION_TIME_MINUTES()));

        tokenRepo.save(verificationToken);
    }

    private void sendVerificationEmail(String to, String token) {
        String subject = "Xác nhận đăng ký tài khoản";
        String confirmationUrl = "http://localhost:8080/SmartParkingManagementApp/api/users/confirm?token=" + token;
        String message = "Vui lòng nhấn vào đường link dưới đây để kích hoạt tài khoản của bạn:\r\n" + confirmationUrl;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom("thanhvu080803@gmail.com");

        mailSender.send(email);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return tokenRepo.findByToken(token);
    }

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    @Transactional
    public boolean confirmUser(String token) {
        try {
            // Tìm VerificationToken theo token
            VerificationToken verificationToken = tokenRepo.findByToken(token);

            if (verificationToken == null) {
                throw new RuntimeException("Invalid verification token");
            }

            // Tìm User dựa trên ID từ VerificationToken
            Optional<User> userOpt = userRepo.findById(verificationToken.getUser().getId());

            if (!userOpt.isPresent()) {
                throw new RuntimeException("User not found");
            }

            User user = userOpt.get();
            user.setEnabled(true);

            // Lưu thông tin User đã cập nhật
            userRepo.save(user);

            // Xóa VerificationToken
            tokenRepo.delete(verificationToken);

            return true;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while confirming the user", e);
        }
    }

    @Override
    public void enableUser(User user) {
        this.userRepo.enableUser(user);
    }

    @Transactional
    @Override
    public DTOUserResponse updateUser(Integer id, DTOUserUpdateRequest dtoUserUpdateRequest, MultipartFile avatar) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        if (dtoUserUpdateRequest.getFirstName() != null) {
            user.setFirstName(dtoUserUpdateRequest.getFirstName());
        }
        if (dtoUserUpdateRequest.getLastName() != null) {
            user.setLastName(dtoUserUpdateRequest.getLastName());
        }
        if (dtoUserUpdateRequest.getPhone() != null) {
            user.setPhone(dtoUserUpdateRequest.getPhone());
        }
        if (dtoUserUpdateRequest.getAddress() != null) {
            user.setAddress(dtoUserUpdateRequest.getAddress());
        }

        if (avatar != null && !avatar.isEmpty()) {
            try {
                Map<?, ?> uploadResult = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap());
                String avatarUrl = (String) uploadResult.get("url");
                user.setAvatar(avatarUrl);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload avatar", e);
            }
        }

        user = userRepo.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    @Transactional
    public void changePassword(Integer userId, ChangePasswordRequest changePasswordRequest) {
        Optional<User> optionalUser = userRepo.findById(userId);

        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        User user = optionalUser.get();

        // Kiểm tra mật khẩu cũ
        if (!passEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        // Cập nhật mật khẩu mới
        user.setPassword(passEncoder.encode(changePasswordRequest.getNewPassword()));

        // Lưu người dùng với mật khẩu và các trường khác đã được cập nhật
        userRepo.save(user);
    }

}
