package com.akademiakodu.blog.demo.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String userName;

    String userPassword;

    String email;

    @Embedded
    private AuditEntity auditEntity = new AuditEntity(); //tworzy kolumny 'roozwijajace' w baziem dodatkowe elementy zwiazane z postem z AuditEntity

    @OneToMany (mappedBy = "user")
    @JsonBackReference
    List<Post> posts = new ArrayList<>();

    @OneToMany (mappedBy = "user")
    @JsonBackReference
    List<PostComment> comments = new ArrayList<>();
//
//    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.MERGE})
//    @JoinTable(name = "mapPostTag",
//            joinColumns = {@JoinColumn(name = "postId")},
//            inverseJoinColumns = {@JoinColumn(name="tagId")})
//    Set<Tag> tags = new HashSet<>(); //Set daje unikalne wartosci + nie trzyma porzadku dodawania. Jeśli tu jest tags,
//    // to w mappedBy w Post myusi byc @ManyToMany(mappedBy = "tags")


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public AuditEntity getAuditEntity() {
        return auditEntity;
    }

    public void setAuditEntity(AuditEntity auditEntity) {
        this.auditEntity = auditEntity;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<PostComment> getComments() {
        return comments;
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(){}

    public User(String title, String content) {
        this.userName = title;
        this.userPassword = content;
    }
}
