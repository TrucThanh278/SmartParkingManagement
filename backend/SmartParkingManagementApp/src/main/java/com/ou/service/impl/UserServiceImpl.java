package com.ou.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.dto.request.DTOUserRequest;
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

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (user == null) {
            LOGGER.log(Level.SEVERE, "User not found: {0}", username);
            throw new UsernameNotFoundException("User not found: " + username);
        }

        if (user.getRoleId() == null) {
            LOGGER.log(Level.SEVERE, "User role not found: {0}", username);
            throw new UsernameNotFoundException("User role not found: " + username);
        }

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
        if (user == null) {
            LOGGER.log(Level.SEVERE, "User not found: {0}", username);
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public User addUser(DTOUserRequest dtoUserRequest, MultipartFile avatar) {
        try {
            // Log dữ liệu đầu vào
            LOGGER.log(Level.INFO, "Adding user with details: {0}", dtoUserRequest);
            LOGGER.log(Level.INFO, "Avatar file: {0}", avatar != null ? avatar.getOriginalFilename() : "No avatar");

            // Validate input
            if (dtoUserRequest == null || avatar == null) {
                LOGGER.log(Level.SEVERE, "DTOUserRequest or avatar cannot be null");
                throw new IllegalArgumentException("DTOUserRequest or avatar cannot be null");
            }

            User user = userMapper.toUser(dtoUserRequest);
            user.setPassword(passEncoder.encode(user.getPassword()));

            // Log thông tin user sau khi mã hóa mật khẩu
            LOGGER.log(Level.INFO, "User to be saved: {0}", user);

            // Set default role
            Role defaultRole;
            try {
                defaultRole = roleRepo.findById(1L);
            } catch (Exception e) {
                throw new RuntimeException("Default role not found with ID: 1", e);
            }
            user.setRoleId(defaultRole);
            // Log thông tin role được gán cho user
            LOGGER.log(Level.INFO, "Role assigned to user: {0}", defaultRole);

            // Upload avatar
            if (!avatar.isEmpty()) {
                try {
                    LOGGER.log(Level.INFO, "Uploading avatar...");
                    Map<?, ?> uploadResult = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap());
                    String avatarUrl = (String) uploadResult.get("url");
                    user.setAvatar(avatarUrl);
                    LOGGER.log(Level.INFO, "Avatar uploaded successfully: {0}", avatarUrl);
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Failed to upload avatar", e);
                    throw new RuntimeException("Failed to upload avatar", e);
                }
            } else {
                LOGGER.log(Level.INFO, "No avatar uploaded");
            }

            // Save user
            user = userRepo.save(user);
            LOGGER.log(Level.INFO, "User saved successfully: {0}", user);

            // Create verification token
            String token = UUID.randomUUID().toString();
            LOGGER.log(Level.INFO, "Creating verification token: {0}", token);
            createVerificationToken(user, token);

            // Send verification email
            LOGGER.log(Level.INFO, "Sending verification email to: {0}", user.getEmail());
            sendVerificationEmail(user.getEmail(), token);

            return user;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to add user", e);
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
        return tokenRepo.findByToken(token).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

//@Override
//public boolean confirmUser(String token) {
//    try {
//        VerificationToken verificationToken = tokenRepo.findByToken(token);
//
//        if (verificationToken == null) {
//            throw new RuntimeException("Invalid verification token");
//        }
//
//        User user = userRepo.findById(verificationToken.getUser().getId());
//
//        if (user == null) {
//            throw new RuntimeException("User not found");
//        }
//
//        user.setActive(true);
//        userRepo.save(user);
//        tokenRepo.delete(verificationToken);
//
//        return true;
//    } catch (Exception e) {
//        throw new RuntimeException("An error occurred while confirming the user", e);
//    }
//}
@Override
@Transactional
public boolean confirmUser(String token) {
    try {
        LOGGER.info("Attempting to confirm user with token: " + token);

        // Tìm VerificationToken theo token
        VerificationToken verificationToken = tokenRepo.findByToken(token);

        if (verificationToken == null) {
            LOGGER.warning("Invalid verification token: " + token);
            throw new RuntimeException("Invalid verification token");
        }

        // Tìm User dựa trên ID từ VerificationToken
        Optional<User> userOpt = userRepo.findById(verificationToken.getUser().getId());

        if (!userOpt.isPresent()) {
            LOGGER.warning("User not found with ID: " + verificationToken.getUser().getId());
            throw new RuntimeException("User not found");
        }

        User user = userOpt.get();
        user.setActive(true);

        // Lưu thông tin User đã cập nhật
        userRepo.save(user);

        // Xóa VerificationToken
        tokenRepo.delete(verificationToken);

        LOGGER.info("User successfully confirmed");
        return true;
    } catch (Exception e) {
        LOGGER.severe("An error occurred while confirming the user dasdsad: " + e.getMessage());
        throw new RuntimeException("An error occurred while confirming the user Thnh Vu 123213", e);
    }
}


}

//    @Override
//    public User addUser(DTOUserRequest dtoUserRequest, MultipartFile avatar) {
//        User user = userMapper.toUser(dtoUserRequest);
//
//        user.setPassword(passEncoder.encode(user.getPassword()));
//
//        Role defaultRole;
//        try {
//            defaultRole = roleRepo.findById(1L);
//        } catch (Exception e) {
//            throw new RuntimeException("Default role not found with ID: 1", e);
//        }
//        user.setRoleId(defaultRole);
//
//        // Xử lý upload avatar lên Cloudinary và gán URL vào user
//        if (avatar != null && !avatar.isEmpty()) {
//            try {
//                Map uploadResult = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap());
//                String avatarUrl = (String) uploadResult.get("url");
//                user.setAvatar(avatarUrl);
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to upload avatar", e);
//            }
//        }
//
//        // Lưu người dùng vào database
//        return userRepo.save(user);
//    }
//    @Override
//    @Transactional
//    public User addUser(DTOUserRequest dtoUserRequest, MultipartFile avatar) {
//        User user = userMapper.toUser(dtoUserRequest);
//        user.setPassword(passEncoder.encode(user.getPassword()));
//        Role defaultRole = roleRepo.findById(1L).orElseThrow(() -> new RuntimeException("Default role not found"));
//        user.setRoleId(defaultRole);
//        user.setIsEnabled(false); // Tài khoản chưa được kích hoạt
//
//        // Xử lý upload avatar lên Cloudinary
//        if (avatar != null && !avatar.isEmpty()) {
//            try {
//                Map uploadResult = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap());
//                String avatarUrl = (String) uploadResult.get("url");
//                user.setAvatar(avatarUrl);
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to upload avatar", e);
//            }
//        }
//
//        userRepo.save(user);
//
//        // Tạo mã xác thực
//        String token = UUID.randomUUID().toString();
//        VerificationToken verificationToken = new VerificationToken();
//        verificationToken.setToken(token);
//        verificationToken.setUser(user);
//        verificationToken.setExpiryDate(new Date(System.currentTimeMillis() + 86400000)); // 24 giờ
//        tokenRepo.save(verificationToken);
//
//        // Gửi email xác thực
//        emailService.sendVerificationEmail(user.getEmail(), token);
//
//        return user;
//    }
//    @Override
//    public User addUser(Map<String, String> params, MultipartFile avatar) {
//        // Log các tham số nhận được
//        Logger.getLogger(UserServiceImpl.class.getName()).info("Received parameters: " + params);
//
//        // Lấy và kiểm tra mật khẩu
//        String password = params.get("password");
//        if (password == null || password.isEmpty()) {
//            Logger.getLogger(UserServiceImpl.class.getName()).severe("Password is null or empty");
//            throw new IllegalArgumentException("Password cannot be null or empty");
//        }
//
//        // Tạo đối tượng User và thiết lập thông tin
//        User u = new User();
//        u.setFirstName(params.get("firstName"));
//        u.setLastName(params.get("lastName"));
//        u.setPhone(params.getOrDefault("phone", "9999999999"));
//        u.setEmail(params.getOrDefault("email", "a@gmail.com"));
//        u.setAddress(params.getOrDefault("address", "123 Main St"));
//        u.setUsername(params.get("username"));
//        u.setPassword(this.passEncoder.encode(password)); // Mã hóa mật khẩu
//
//        // Log mật khẩu đã được mã hóa
//        Logger.getLogger(UserServiceImpl.class.getName()).info("Encoded password: " + u.getPassword());
//
//        // Tìm và thiết lập vai trò
//        Role role = this.roleRepository.findByName("ROLE_USER");
//        if (role == null) {
//            Logger.getLogger(UserServiceImpl.class.getName()).severe("Role not found: ROLE_USER");
//            throw new RuntimeException("Role not found: ROLE_USER");
//        }
//        u.setRoleId(role);
//
//        // Xử lý ảnh đại diện
//        if (avatar != null && !avatar.isEmpty()) {
//            try {
//                Logger.getLogger(UserServiceImpl.class.getName()).info("Uploading avatar...");
//
//                // Tạo tệp tạm thời
//                Path tempFile = Files.createTempFile("avatar", ".tmp");
//                try ( InputStream inputStream = avatar.getInputStream()) {
//                    Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
//                }
//
//                // Tải lên tệp từ đường dẫn tạm thời
//                Map<String, Object> uploadResult = this.cloudinary.uploader().upload(tempFile.toFile(),
//                        ObjectUtils.asMap("resource_type", "auto"));
//
//                Logger.getLogger(UserServiceImpl.class.getName()).info("Upload result: " + uploadResult);
//
//                // Lấy URL của ảnh đại diện từ kết quả tải lên
//                String avatarUrl = (String) uploadResult.get("secure_url");
//                if (avatarUrl != null) {
//                    u.setAvatar(avatarUrl);
//                    Logger.getLogger(UserServiceImpl.class.getName()).info("Avatar uploaded successfully: " + avatarUrl);
//                } else {
//                    Logger.getLogger(UserServiceImpl.class.getName()).severe("Upload result does not contain 'secure_url'");
//                    throw new RuntimeException("Upload result does not contain 'secure_url'");
//                }
//
//                // Xóa tệp tạm thời sau khi sử dụng
//                Files.deleteIfExists(tempFile);
//
//            } catch (IOException ex) {
//                Logger.getLogger(UserServiceImpl.class.getName()).severe("Error uploading avatar: " + ex.getMessage());
//                throw new RuntimeException("Error uploading avatar", ex);
//            }
//        } else {
//            Logger.getLogger(UserServiceImpl.class.getName()).info("No avatar uploaded");
//        }
//
//        // Lưu người dùng vào cơ sở dữ liệu
//        try {
//            this.userRepo.addUser(u);
//        } catch (Exception ex) {
//            Logger.getLogger(UserServiceImpl.class.getName()).severe("Error adding user: " + ex.getMessage());
//            ex.printStackTrace(); // Để biết thêm chi tiết về lỗi
//            throw new RuntimeException("Error adding user", ex);
//        }
//
//        return u;
//    }
