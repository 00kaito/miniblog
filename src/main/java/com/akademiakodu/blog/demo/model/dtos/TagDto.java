package com.akademiakodu.blog.demo.model.dtos;

import com.akademiakodu.blog.demo.model.entities.Post;

import java.util.Set;

//dto tworzymy, aby nie przekazywac ancji do warstwy widoku (zabezpiczenie na przyszlosc, kiedy zmienimy cos w encji, zeby
// nie musiec pozniej wszystkiego zmieniac w widoku

public class TagDto {
    private Long id;

    private String Tagname;
    private Set<Post> posts;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagname() {
        return Tagname;
    }

    public void setTagname(String tagname) {
        Tagname = tagname;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
