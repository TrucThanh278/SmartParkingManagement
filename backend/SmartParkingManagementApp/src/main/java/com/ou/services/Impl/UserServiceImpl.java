/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.services.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.pojo.User;
import com.ou.repositories.UserRepository;
import com.ou.services.UserService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trucn
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private Cloudinary cloundinary;
    
    @Override
    public List<User> getUsers() {
        return this.userRepository.getUsers();
    }

    @Override
    public User getUserDetail(int id) {
        return this.userRepository.getUserDetail(id);
    }
    
    @Override
    public void addOrUpdate(User u){
        if(!u.getFile().isEmpty()){
            try {
                Map res = this.cloundinary.uploader().upload(u.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.userRepository.addOrUpdateUser(u);
    }
    
    
}
