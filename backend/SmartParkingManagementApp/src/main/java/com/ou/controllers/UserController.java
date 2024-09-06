/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.controllers;

import com.ou.dto.request.UserRequestDTO;
import com.ou.dto.response.UserResponseDTO;
import com.ou.pojo.User;
import com.ou.pojo.Vehicle;
import com.ou.services.RoleService;
import com.ou.services.UserService;
import com.ou.services.VehicleService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author trucn
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("roles")
    public void roles(Model model) {
        model.addAttribute("roles", this.roleService.getRoles());
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = this.userService.getUsers();
        model.addAttribute("users", users);
        return "showUsers";
    }

    @GetMapping("/user/{id}")
    public String getUserDetail(Model model, @PathVariable("id") int id) {
        User user = this.userService.getUserDetail(id);
        List<Vehicle> vehicles = this.vehicleService.getVehicleWithUserID(id);
        model.addAttribute("user", user);
        model.addAttribute("vehicles", vehicles);
        return "userDetail";
    }
    
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        try {
            this.userService.deleteUser(id);
        } catch (Exception e) {
            System.err.println("Delete Message: "+ e.getMessage());
        }
        return "redirect:/users";
    }

//    @GetMapping("/user/create")
//    public String getCreateUserPage(Model model) {
//        model.addAttribute("newUser", new User());
//        return "createUser";
//    }

//    @PostMapping("/user/create")
//    public String createUser(Model model, @ModelAttribute(value = "newUser") @Valid User user, BindingResult rs) {
//        if (rs.hasErrors()) {
//             List<FieldError> errors = rs.getFieldErrors();
//         for (FieldError error : errors) {
//         System.out.println(">>>>>>" + error.getField() + " - " +
//         error.getDefaultMessage());
//         }
//            return "createUser";
//        }
//        this.userService.addUser(user);
//        return "redirect:/users";
//    }
}
