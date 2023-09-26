package com.sk.userdatabase.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.userdatabase.entity.UserEntity;
import com.sk.userdatabase.model.User;
import com.sk.userdatabase.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // getAllUsers
    public List<User> getAllUsers() {

        try {
            List<UserEntity> users = userRepository.findAll();
            List<User> customUsers = new ArrayList<>();
            users.stream().forEach(e -> {
                User user = new User();
                BeanUtils.copyProperties(e, user);
                customUsers.add(user);
            });
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    // create/add User
    public String addUser(UserEntity user) {
        try {
            if (!userRepository.existsByFirstNameAndLastName(user.getFirstName(), user.getLastName())) {
                userRepository.save(user);
                return "User added successfully";
            } else {
                return "This user already exists in the database.";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    // delete User
    public String removeUser(UserEntity user) {
        try {
            if (userRepository.existsByFirstNameAndLastName(user.getFirstName(), user.getLastName())) {
                userRepository.delete(user);
                return "User deleted successfully";
            } else {
                return "User does not exists.";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    // Update User
    public String updateUser(UserEntity user) {
        try {
            if (userRepository.existsById(user.getId())) {
                userRepository.save(user);
                return "User updated successfully";
            } else {
                return "User updating failed.";
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
