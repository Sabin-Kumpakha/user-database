package com.sk.userdatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.userdatabase.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public boolean existsByFirstNameAndLastName(String firstName, String lastName);

    public boolean existsById(int id);

}
