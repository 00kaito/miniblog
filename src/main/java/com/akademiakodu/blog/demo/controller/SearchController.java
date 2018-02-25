package com.akademiakodu.blog.demo.controller;

import com.akademiakodu.blog.demo.model.entities.Post;
import com.akademiakodu.blog.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    PostRepository postRepository;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchGet(Model model,
                            @RequestParam(value = "searchPhrase") String searchPhrase) {
        List<Post> posts = postRepository.findAllByTitleContainsOrContentContains(searchPhrase, searchPhrase);
        model.addAttribute("posts", posts);
        model.addAttribute("searchPhrase", searchPhrase);
        return "posts";
    }


}
