/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.services;

import com.ou.pojo.User;
import java.util.List;

/**
 *
 * @author trucn
 */
public interface UserService {
    public List<User> getUsers();
    public User getUserDetail(int id);
    public void addOrUpdate(User u);
}
