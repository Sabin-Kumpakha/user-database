package com.sk.userdatabase.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sk.userdatabase.entity.UserEntity;
import com.sk.userdatabase.model.User;
import com.sk.userdatabase.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // getAllUsers
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<UserEntity> users = userRepository.findAll();
            List<User> customUsers = users.stream()
                    .map(userEntity -> {
                        User user = new User();
                        BeanUtils.copyProperties(userEntity, user);
                        return user;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(customUsers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // create/add User
    public ResponseEntity<String> addUser(UserEntity user) {
        try {
            if (!userRepository.existsByFirstNameAndLastName(user.getFirstName(), user.getLastName())) {
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"User added successfully\"}");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"error\": \"This user already exists in the database.\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Internal server error\"}");
        }
    }

    // delete User
    public ResponseEntity<String> removeUser(UserEntity user) {
        try {
            if (userRepository.existsByFirstNameAndLastName(user.getFirstName(), user.getLastName())) {
                userRepository.delete(user);
                return ResponseEntity.status(HttpStatus.OK).body("{'message':'User deleted successfully'}");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{'error':'User does not exist.'}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{'error':'Internal server error'}");
        }
    }

    // update User
    public ResponseEntity<String> updateUser(UserEntity user) {
        try {
            if (userRepository.existsById(user.getId())) {
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("{'message':'User updated successfully'}");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{'error':'User updating failed.'}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{'error':'Internal server error'}");
        }
    }
}
