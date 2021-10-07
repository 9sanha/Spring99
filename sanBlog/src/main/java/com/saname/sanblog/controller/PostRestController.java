package com.saname.sanblog.controller;

import com.saname.sanblog.dto.PostDto;
import com.saname.sanblog.model.Post;
import com.saname.sanblog.repository.PostRepository;
import com.saname.sanblog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class PostRestController {
    private final PostRepository postRepository;
    private final PostService postService;

    public PostRestController(final PostRepository myBlogRepository, final PostService postService) {
        this.postRepository = myBlogRepository;
        this.postService = postService;
    }

    @GetMapping("/api/post")
    public List<Post> getPost(){

        List<Post> listblog = postRepository.findAll();
        Comparator<Post> compare = Comparator
                .comparing(Post::getCreatedAt).reversed();
        List<Post> sortedList = listblog.stream()
                .sorted(compare)
                .collect(Collectors.toList());
        return sortedList;
    }


    @PostMapping({"/api/post"})
    public Boolean savePost(@RequestBody PostDto requestDto) {
        if (this.postService.filter(requestDto)) {
            Post post = new Post(requestDto);
            this.postRepository.save(post);
            return true;
        } else {
            return false;
        }
    }

    @GetMapping({"/api/detail/{id}"})
    public Optional<Post> getPostingDetail(@PathVariable Long id) {
        return this.postRepository.findById(id);
    }


}