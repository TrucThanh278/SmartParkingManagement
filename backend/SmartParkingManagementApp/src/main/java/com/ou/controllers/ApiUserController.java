package com.ou.controllers;

import com.ou.pojo.User;
import com.ou.components.JwtService;
import com.ou.dto.request.ChangePasswordRequestDTO;
import com.ou.dto.request.UserRequestDTO;
import com.ou.dto.request.UserUpdateRequestDTO;
import com.ou.dto.response.UserResponseDTO;
import com.ou.pojo.VerificationToken;
import com.ou.services.UserService;
import java.security.Principal;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
public class ApiUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiUserController.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            if (this.userService.authUser(user.getUsername(), user.getPassword())) {
                String token = this.jwtService.generateTokenLogin(user.getUsername());
                return new ResponseEntity<>(token, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            LOGGER.error("Error during login", e);
            return new ResponseEntity<>("An error occurred during login", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/users", consumes = "multipart/form-data", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<?> addUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestPart("avatar") MultipartFile avatar) {
        try {
            // Create UserRequestDTO object from parameters
            UserRequestDTO dtoUserRequest = UserRequestDTO.builder()
                    .username(username)
                    .password(password)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .phone(phone)
                    .address(address)
                    .avatar(null)
                    .build();

            User user = userService.addUser(dtoUserRequest, avatar);
            UserResponseDTO userResponse = userService.getDTOUserByUsername(user.getUsername());
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            LOGGER.error("Error adding user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the user");
        }
    }

    @GetMapping(path = "/current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<UserResponseDTO> details(Principal user) {
        try {
            UserResponseDTO userResponse = this.userService.getDTOUserByUsername(user.getName());
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error fetching user details", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/users/confirm")
    @CrossOrigin
    public ResponseEntity<String> confirmUser(@RequestParam("token") String token) {
        try {
            VerificationToken verificationToken = this.userService.getVerificationToken(token);

            if (verificationToken == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
            }

            if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token expired.");
            }

            User u = verificationToken.getUser();
            this.userService.enableUser(u);
            return ResponseEntity.ok("User successfully activated.");
        } catch (Exception e) {
            LOGGER.error("Error confirming user with token: {}", token, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(path = "/users/{id}", consumes = "multipart/form-data", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<?> updateUser(
            @PathVariable("id") Integer id,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "address", required = false) String address,
            @RequestPart(value = "avatar", required = false) MultipartFile avatar) {
        try {
            UserUpdateRequestDTO dtoUserUpdateRequest = UserUpdateRequestDTO.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .phone(phone)
                    .address(address)
                    .build();

            UserResponseDTO updatedUser = userService.updateUser(id, dtoUserUpdateRequest, avatar);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the user.");
        }
    }

    @PostMapping("/{id}/change-password")
    @CrossOrigin
    public ResponseEntity<?> changePassword(
            @PathVariable Integer id,
            @RequestBody ChangePasswordRequestDTO changePasswordRequest) {

        try {
            this.userService.changePassword(id, changePasswordRequest);
            return ResponseEntity.ok("Password changed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
