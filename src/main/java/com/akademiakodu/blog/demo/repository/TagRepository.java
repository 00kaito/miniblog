package com.akademiakodu.blog.demo.repository;

import com.akademiakodu.blog.demo.model.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{

}
