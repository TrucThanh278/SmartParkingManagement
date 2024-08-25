package com.ou.service;

import com.ou.dto.request.DTOUserRequest;
import com.ou.dto.response.DTOUserResponse;
import com.ou.pojo.User;
import com.ou.pojo.VerificationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ADMIN
 */
public interface UserService extends UserDetailsService {

    User getUserByUsername(String username);

    boolean authUser(String username, String password);

    User addUser(DTOUserRequest dtoUserRequest, MultipartFile avatar);

    DTOUserResponse getDTOUserByUsername(String username);
    
    VerificationToken getVerificationToken(String token);
    
    void saveUser(User user);
    
    boolean confirmUser(String token); // Thêm phương thức này
}
