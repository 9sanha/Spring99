package com.saname.myblog.controller;

import com.saname.myblog.models.MyBlog;
import com.saname.myblog.dto.MyBlogDto;
import com.saname.myblog.repository.MyBlogRepository;
import com.saname.myblog.sevice.MyBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MyBlogRestController {

    private final MyBlogRepository myBlogRepository;
    private final MyBlogService myBlogService;


    @GetMapping("/api/post")
    public List<MyBlog> getPost(){

        List<MyBlog> listblog = myBlogRepository.findAll();
        Comparator<MyBlog> compare = Comparator
                .comparing(MyBlog::getCreatedAt).reversed();
        List<MyBlog> sortedList = listblog.stream()
                .sorted(compare)
                .collect(Collectors.toList());
        return sortedList;
    }


    @PostMapping("/api/post")
    public Boolean savePost(@RequestBody MyBlogDto requestDto){ //뇌피셜
        if(myBlogService.filter(requestDto)){
            MyBlog myBlog = new MyBlog(requestDto);
            myBlogRepository.save(myBlog);
            return true;
        }else {
            return false;
        }
    }

    @GetMapping("/api/detail/{id}")
    public Optional<MyBlog> getPostingDetail(@PathVariable Long id){
        return myBlogRepository.findById(id);
    }

    @DeleteMapping("/api/detail/{id}")
    public void deletePostingComment(@PathVariable Long id){
        myBlogRepository.deleteById(id);
    }
}
