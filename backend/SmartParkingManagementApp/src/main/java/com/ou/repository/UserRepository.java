package com.ou.repository;

import com.ou.pojo.User;
import java.util.Optional;

public interface UserRepository {

    User getUserByUsername(String username);

    boolean authUser(String username, String password);

    User addUser(User user);

    User save(User user);

    boolean userExistsByUsername(String username);

    Optional<User> findById(Integer id);
    
    void enableUser(User u);
}
