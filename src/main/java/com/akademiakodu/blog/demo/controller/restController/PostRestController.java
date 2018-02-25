package com.akademiakodu.blog.demo.controller.restController;

import com.akademiakodu.blog.demo.model.dtos.PostDto;
import com.akademiakodu.blog.demo.model.entities.Post;
import com.akademiakodu.blog.demo.repository.PostRepository;
import com.akademiakodu.blog.demo.repository.TagRepository;
import com.akademiakodu.blog.demo.repository.UserRepository;
import javafx.geometry.Pos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostRestController {

    PostRepository postRepository;
    TagRepository tagRepository;
    UserRepository userRepository;

    @RequestMapping(value = "/api/post", method = RequestMethod.POST)
    public ResponseEntity<PostDto> createPost(@RequestParam String postTitle,
                                              @RequestParam String content,
                                              @RequestParam Long userId){
        Post post = new Post();
        post.setTitle(postTitle);
        post.setContent(content);
        post.setUser(userRepository.getOne(userId));
        postRepository.save(post);

        PostDto postDto = (new ModelMapper().map(post, PostDto.class));
        return ResponseEntity.ok().body(postDto);
    }

    @PostMapping(value = "/api/post/updateArticle")
    public ResponseEntity<Post> addPost(@RequestParam String title,
                                        @RequestParam String content,
                                        @RequestParam Long postId){
        Post post = postRepository.getOne(postId);
        post.setTitle(title);
        post.setContent(content);
        postRepository.save(post);

        return ResponseEntity.ok().body(post);
    }

    @Autowired
    public PostRestController(PostRepository postRepository, TagRepository tagRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

}
