package com.akademiakodu.blog.demo.controller;

import com.akademiakodu.blog.demo.model.entities.Post;
import com.akademiakodu.blog.demo.model.entities.PostComment;
import com.akademiakodu.blog.demo.repository.PostRepository;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/post/{id}")
    public String getSinglePost(Model model, @PathVariable Long id) {
//        Post post = postRepository.findById(id).get();

        Optional<Post> postOpional = postRepository.findById(id);
        postOpional.ifPresent(post ->{
            model.addAttribute("post", post);
        } );

        return "single";
    }

    @PostMapping("/addComment")
    public String addComment(Model model, @RequestParam Long postId, @RequestParam String comment){
//        Post post = postRepository.findById(postId).get();
//ctrl + alt + v - przypisanie do zmiennej
        PostComment newPostComment = new PostComment();
        newPostComment.setComment(comment);

        //getOne lub jak wyzje z Optional<>
        //Post post = postRepository.getOne(postId);
        //post.addComment(newPostComment);

        Optional<Post> postOptional = postRepository.findById(postId);
        postOptional.ifPresent(asd->{
            asd.addComment(newPostComment);
            postRepository.save(asd);
        });

        return "redirect:/post/"+postId;
    }
}
