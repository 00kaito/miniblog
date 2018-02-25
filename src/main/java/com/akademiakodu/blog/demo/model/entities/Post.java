package com.akademiakodu.blog.demo.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.engine.internal.Cascade;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.*;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String title;

    String content;

    @Embedded
    private AuditEntity auditEntity = new AuditEntity(); //tworzy kolumny 'roozwijajace' w baziem dodatkowe elementy zwiazane z postem z AuditEntity


    @OneToMany (mappedBy = "post", cascade = CascadeType.MERGE) //propaguje informacje od góry w dół, kiedy dodajemy post, który w momencie dodawania
    // nie ma jeszcze id, wtedy ta funkcja jest potrzebna
    List<PostComment> comments = new ArrayList<>();


    //w przypadku tabeli dwukierunkowej manytomany utworzy sie dodatkowa 'posrednia' tabela asocjacyjna. Bez join stworzy 2
    // tabele, trzeba utworzyc Join, aby byl wlasciciel relacji + ustalic nazwe dla tabeli
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.MERGE})
    @JoinTable(name = "mapPostTag",
            joinColumns = {@JoinColumn(name = "postId")},
            inverseJoinColumns = {@JoinColumn(name="tagId")})
    Set<Tag> tags = new HashSet<>(); //Set daje unikalne wartosci + nie trzyma porzadku dodawania.
    // Jeśli tu jest tags, to w mappedBy w Post myusi byc @ManyToMany(mappedBy = "tags")
    public Set<Tag> getTags() {
        return tags;
    }

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "userId")
    User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addComment(PostComment postComment){
        comments.add(postComment);
        postComment.setPost(this);
    }

    public void removeComment(PostComment postComment){
        comments.remove(postComment);
        postComment.setPost(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post(){}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public List<PostComment> getComments() {
        return comments;
    }

    public AuditEntity getAuditEntity() {
        return auditEntity;
    }

    public void setAuditEntity(AuditEntity auditEntity) {
        this.auditEntity = auditEntity;
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", comment size: " + comments.size() +
                '}';
    }
}
