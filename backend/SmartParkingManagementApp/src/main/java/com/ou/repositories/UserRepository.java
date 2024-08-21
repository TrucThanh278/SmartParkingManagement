/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.repositories;

import com.ou.pojo.User;
import java.util.List;

/**
 *
 * @author trucn
 */
public interface UserRepository {
    public List<User> getUsers();
    public User getUserDetail(int id);
    public User getUserByEmail(String email);
//    public void addOrUpdateUser(User u);
}
