package com.codigo.alpha.apispringbootcrud.controllers;

import com.codigo.alpha.apispringbootcrud.entity.User;
import com.codigo.alpha.apispringbootcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/AddUser")
    public User addUser(@RequestBody User user){
        return service.saveUser(user);
    }

    @GetMapping("/Users")
    public List<User> users(){
        return service.getUser();
    }

    @GetMapping("/Find/{id}")
    public User findById(@PathVariable Integer id){
        return service.findById(id);
    }


    @DeleteMapping("/Delete/{id}")
    public String delete(@PathVariable Integer id){
        return service.deleteUser(id);
    }

    @PutMapping()
    public User updateUser(@RequestBody User user){
        return service.updateUser(user);
    }


}
