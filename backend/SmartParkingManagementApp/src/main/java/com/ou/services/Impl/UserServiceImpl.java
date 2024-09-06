/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.services.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.dto.request.ChangePasswordRequestDTO;
import com.ou.dto.request.UserRequestDTO;
import com.ou.dto.request.UserUpdateRequestDTO;
import com.ou.dto.response.UserResponseDTO;
import com.ou.mappers.UserMapper;
import com.ou.pojo.Role;
import com.ou.pojo.User;
import com.ou.pojo.VerificationToken;
import com.ou.repositories.RoleRepository;
import com.ou.repositories.UserRepository;
import com.ou.repository.VerificationTokenRepository;
import com.ou.services.UserService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author trucn
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private VerificationTokenRepository tokenRepo;
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Override
    public List<User> getUsers() {
        return this.userRepository.getUsers();
    }

    @Override
    public User getUserDetail(int id) {
        return this.userRepository.getUserDetail(id);
    }

    @Override
    public User getUserByUsername(String email) {
        return this.userRepository.getUserByUsername(email);
    }
    
    @Override
    public boolean authUser(String username, String password) {
        return this.userRepository.authUser(username, password);
    }
    
    @Override
    public void deleteUser(Integer id){
        this.userRepository.deleteUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoleId().getName()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public UserResponseDTO getDTOUserByUsername(String username) {
        User user = getUserByUsername(username);
        return userMapper.toUserResponse(user);
    }

    @Override
    public User addUser(UserRequestDTO dtoUserRequest, MultipartFile avatar) {
        try {

            User user = userMapper.toUser(dtoUserRequest);
            user.setPassword(passEncoder.encode(user.getPassword()));

            // Set default role
            Role defaultRole;
            try {
                defaultRole = this.roleRepository.findById(1L);
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
            user = this.userRepository.addUser(user);

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
        this.userRepository.addUser(user);
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
            Optional<User> userOpt = this.userRepository.findById(verificationToken.getUser().getId());

            if (!userOpt.isPresent()) {
                throw new RuntimeException("User not found");
            }

            User user = userOpt.get();
            user.setEnabled(true);

            // Lưu thông tin User đã cập nhật
            this.userRepository.addUser(user);

            // Xóa VerificationToken
            tokenRepo.delete(verificationToken);

            return true;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while confirming the user", e);
        }
    }

    @Override
    public void enableUser(User user) {
        this.userRepository.enableUser(user);
    }

    @Transactional
    @Override
    public UserResponseDTO updateUser(Integer id, UserUpdateRequestDTO dtoUserUpdateRequest, MultipartFile avatar) {
        Optional<User> optionalUser = this.userRepository.findById(id);
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

        user = this.userRepository.addUser(user);
        return userMapper.toDTO(user);
    }

    @Override
    public void changePassword(Integer userId, ChangePasswordRequestDTO changePasswordRequest) {
        Optional<User> optionalUser = this.userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        User user = optionalUser.get();

        if (!passEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        user.setPassword(passEncoder.encode(changePasswordRequest.getNewPassword()));

        this.userRepository.addUser(user);
    }

    @Override
    public User getUserByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public UserResponseDTO getMyInfo(){
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = this.userRepository.getUserByUsername(username);
        
        return this.userMapper.toUserResponse(user);
    }
}
