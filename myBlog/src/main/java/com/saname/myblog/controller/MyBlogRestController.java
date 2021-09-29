package com.saname.myblog.controller;

import com.saname.myblog.models.MyBlog;
import com.saname.myblog.models.MyBlogDto;
import com.saname.myblog.models.MyBlogRepository;
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
        //날짜순 정렬 구현 필요
        List<MyBlog> listblog = myBlogRepository.findAll();
        Comparator<MyBlog> compare = Comparator
                .comparing(MyBlog::getCreatedAt).reversed();
        List<MyBlog> sortedList = listblog.stream()
                .sorted(compare)
                .collect(Collectors.toList());
        return sortedList;
    }

    //public void savePost(@RequestBody String title,@RequestBody String contents,@RequestBody String nickname){
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
}
