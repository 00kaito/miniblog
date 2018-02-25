package com.akademiakodu.blog.demo.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String comment;


//    @Temporal(TemporalType.TIMESTAMP) //ta adnotacja mowi na co chcemy to zmapowac w bazie
//    private Date added = new Date();

    private AuditEntity audit = new AuditEntity();

    @ManyToOne
    @JoinColumn(name = "postId") //nazwa kolumny po ktorej bedzi relacja, kolumna wiążąca jest zawsze po stronie 'wiele'
    //hibernate stworzy kolumne postId w tabeli postcomment z celu powiazania tabeli post z postcomment
    private Post post;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "userId")
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AuditEntity getAudit() {
        return audit;
    }

    public void setAudit(AuditEntity audit) {
        this.audit = audit;
    }

    @Override
    public String toString() {
        return "PostComment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", post=" + post +
                '}';
    }
}
