package com.akademiakodu.blog.demo.repository;

import com.akademiakodu.blog.demo.model.entities.Post;
import com.akademiakodu.blog.demo.model.entities.PostComment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTitleContains(String title, Sort by);
    List<Post> findAllByTitleContains(String title);
    List<Post> findAllOrderByTitle(String title);
    List<Post> findAllByTitleOrderByTitleAsc(String title);
    //kiedy skladam '2 zapytania' potrzebna wiecej argumentow, im więcej or i and tym więcej argumentow wymaga
    List<Post> findAllByTitleContainsOrContentContains(String title, String content);
}
