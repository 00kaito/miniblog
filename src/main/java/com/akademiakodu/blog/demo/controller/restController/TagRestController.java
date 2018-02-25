package com.akademiakodu.blog.demo.controller.restController;
import com.akademiakodu.blog.demo.model.dtos.TagDto;
import com.akademiakodu.blog.demo.model.entities.Post;
import com.akademiakodu.blog.demo.model.entities.Tag;
import com.akademiakodu.blog.demo.repository.PostRepository;
import com.akademiakodu.blog.demo.repository.TagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@RestController
public class TagRestController {

    TagRepository tagRepository;
    PostRepository postRepository;

    @Autowired
    public TagRestController(TagRepository tagRepository, PostRepository postRepository) {
        this.tagRepository = tagRepository;
        this.postRepository = postRepository;
    }

    @RequestMapping(value = "/api/tag", method = RequestMethod.POST)
    public ResponseEntity<TagDto> createTag(@RequestParam String tagName){//responseEntity obiekty entity typu Tag
        Tag tag = new Tag();
        tag.setTagName(tagName);
        tagRepository.save(tag);

        TagDto tagDto = (new ModelMapper()).map(tag, TagDto.class);
        return ResponseEntity.ok().body(tagDto);
    }

    @PutMapping(value = "/api/tag/addTagToArticle")
    public ResponseEntity<Post> addTagPost(@RequestParam Long tagId, @RequestParam Long postId){
        Tag tag = tagRepository.getOne(tagId);
        Post post = postRepository.getOne(postId);

        post.getTags().add(tag);
        postRepository.save(post);
        return ResponseEntity.ok().body(post);
    }
}
