package com.akademiakodu.blog.demo.model.entities;

import com.akademiakodu.blog.demo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private AuditEntity auditEntity = new AuditEntity();

    private String tagName;

    //w przypadku tabeli dwukierunkowej manytomany utworzy sie dodatkowa 'posrednia' tabela asocjacyjna
    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
