///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.ou.controllers;
//
//import com.ou.pojo.User;
//import com.ou.components.JwtService;
//import com.ou.dto.response.DTOUserResponse;
//import com.ou.service.UserService;
//import java.security.Principal;
//import java.util.Map;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// *
// * @author ADMIN
// */
//@RestController
//@RequestMapping("/api")
//public class ApiUserController {
//
//    @Autowired
//    private JwtService jwtService;
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/login")
//    @CrossOrigin
//    public ResponseEntity<String> login(@RequestBody User user) {
//        if (this.userService.authUser(user.getUsername(), user.getPassword()) == true) {
//            String token = this.jwtService.generateTokenLogin(user.getUsername());
//
//            return new ResponseEntity<>(token, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
//    }
//
//    @PostMapping(path = "/users",
//            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    @CrossOrigin
//    public ResponseEntity<User> addUser(
//            @RequestParam Map<String, String> params,
//            @RequestParam("avatar") MultipartFile avatar) {
//        try {
//            User user = userService.addUser(params, avatar);
//            return ResponseEntity.ok(user);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    @GetMapping(path = "/current-user", produces = MediaType.APPLICATION_JSON_VALUE)
//    @CrossOrigin
//    public ResponseEntity<DTOUserResponse> details(Principal user) {
//        DTOUserResponse userResponse = this.userService.getDTOUserByUsername(user.getName());
//        return new ResponseEntity<>(userResponse, HttpStatus.OK);
//    }
//}
package com.ou.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.pojo.User;
import com.ou.components.JwtService;
import com.ou.dto.request.DTOUserRequest;
import com.ou.dto.response.DTOUserResponse;
import com.ou.pojo.VerificationToken;
import com.ou.service.UserService;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for handling user-related endpoints.
 *
 * @author ADMIN
 */
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
            // Create DTOUserRequest object from parameters
            DTOUserRequest dtoUserRequest = DTOUserRequest.builder()
                    .username(username)
                    .password(password)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .phone(phone)
                    .address(address)
                    .avatar(null) // Avatar will be handled separately
                    .build();

            // Add user
            User user = userService.addUser(dtoUserRequest, avatar);
            DTOUserResponse userResponse = userService.getDTOUserByUsername(user.getUsername());
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            LOGGER.error("Error adding user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the user");
        }
    }

    @GetMapping(path = "/current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<DTOUserResponse> details(Principal user) {
        try {
            DTOUserResponse userResponse = this.userService.getDTOUserByUsername(user.getName());
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
            LOGGER.info("Received token for confirmation: {}", token);

            VerificationToken verificationToken = userService.getVerificationToken(token);
            
            System.out.println(">>>>>>>> Verification Token Object: " + verificationToken.toString());
            
            if (verificationToken == null) {
                LOGGER.warn("Token not found: {}", token);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
            }

            if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
                LOGGER.warn("Token expired: {}", token);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token expired.");
            }

            verificationToken.getUser().setEnabled(true);
            boolean test = verificationToken.getUser().isEnabled();
//            if (user == null) {
//                LOGGER.warn("User not found for token: {}", token);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found.");
//            }
//
//            user.setEnabled(true);
//            this.userService.saveUser(user);
//            LOGGER.info("User successfully activated: {}", user.getUsername());

            return ResponseEntity.ok("User successfully activated.");
        } catch (Exception e) {
            LOGGER.error("Error confirming user with token: {}", token, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
