package com.saptarshi.SecurityPractice.repository;

import com.saptarshi.SecurityPractice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>
{
    public User findByUsername(String username);
}
