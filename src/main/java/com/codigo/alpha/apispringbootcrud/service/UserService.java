package com.codigo.alpha.apispringbootcrud.service;

import com.codigo.alpha.apispringbootcrud.entity.User;
import com.codigo.alpha.apispringbootcrud.repositoty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User findById(Integer id){
        return repository.findById(id).orElse(null);
    }
    public List<User> getUser(){
        return repository.findAll();
    }

    public User saveUser(User user){
        return repository.save(user);
    }

    public String deleteUser(Integer id){
        repository.deleteById(id);
        return "User removed " + id;
    }

    public User updateUser(User user){
        User existingUser = repository.findById(user.getId()).orElse(null);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAddress(user.getAddress());
        return repository.save(existingUser);
    }

}




