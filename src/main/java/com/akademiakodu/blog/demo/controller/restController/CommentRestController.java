package com.akademiakodu.blog.demo.controller.restController;

import com.akademiakodu.blog.demo.model.dtos.CommentDto;
import com.akademiakodu.blog.demo.model.entities.Post;
import com.akademiakodu.blog.demo.model.entities.PostComment;
import com.akademiakodu.blog.demo.model.entities.User;
import com.akademiakodu.blog.demo.repository.PostRepository;
import com.akademiakodu.blog.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentRestController {

    PostRepository postRepository;
    UserRepository userRepository;

    @PostMapping("/api/comment/add")
    public ResponseEntity<CommentDto> addComment(@RequestParam String comment,
                                                 @RequestParam Long postId,
                                                 @RequestParam Long userId){
        PostComment postComment = new PostComment();
        postComment.setComment(comment);
        postComment.setPost(postRepository.getOne(postId));
        postComment.setUser(userRepository.getOne(userId));

        Post post = postRepository.getOne(postId);
        post.getComments().add(postComment);

        postRepository.save(post);

        CommentDto commentDto = (new ModelMapper().map(postComment, CommentDto.class));
        return ResponseEntity.ok().body(commentDto);
    }

    @Autowired
    public CommentRestController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
}
