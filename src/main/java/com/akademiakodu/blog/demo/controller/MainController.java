package com.akademiakodu.blog.demo.controller;

import com.akademiakodu.blog.demo.model.entities.Post;
import com.akademiakodu.blog.demo.model.entities.PostComment;
import com.akademiakodu.blog.demo.repository.PostRepository;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

//DTO data transfer object, warto poczytac, bo niekoniecznie powinna sie przekazywac encje do warstwy widoku?

@Controller
public class MainController {

    private PostRepository postRepository;

    @GetMapping("/")
    public String getIndexPage(Model model) {
        model.addAttribute("text", "cześć");
        return "index";
    }

    @PostMapping("/addPost")
    public String addPost(Model model, @RequestParam String title, @RequestParam String content) {
        //musimy dawac nazwe dla @requestparam jesli sa inne niz nazwa zmiennej
        Post post = new Post(title, content);

        //dodajemy defaultowy komentarz dla dodawanego wpisu
        PostComment postComment = new PostComment();
        postComment.setComment(title);
        post.addComment(postComment);

//        postComment.setPost(post);
//        List<PostComment> commentList = new ArrayList<>();
//        commentList.add(postComment);
//        post.setComments(commentList);


        postRepository.save(post);
        return "addPost";
    }


    @GetMapping("/addPost")
    public String addPostPage(Model model) {
        return "addPost";
    }

    @GetMapping("/posts")
    public String getAllPosts(Model model) {
        List<Post> posts = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAll();

        for (Post post : postIterable) {
            posts.add(post);
        }
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/{title}")
    public String getPostsByTitle(@PathVariable String title, Model model) {
        List<Post> posts = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAllByTitleContains(title);
        for (Post post : postIterable) {
            posts.add(post);
        }
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/{title}/{sortField}/{direction}")
    public String getPostsByTitle(@PathVariable String title,
                                  @PathVariable String sortField,
                                  @PathVariable String direction,
                                  Model model) {
        List<Post> posts = new ArrayList<>();
        posts = postRepository.findAllByTitleContains(title, Sort.by(Sort.Direction.fromString(direction), sortField));

        model.addAttribute("posts", posts);
        return "posts";
    }

    @Autowired
    public MainController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

}
