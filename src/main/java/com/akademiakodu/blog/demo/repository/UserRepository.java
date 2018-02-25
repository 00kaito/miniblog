package com.akademiakodu.blog.demo.repository;

import com.akademiakodu.blog.demo.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
