package com.saname.amen.controller;

import com.saname.amen.dto.PostDto;
import com.saname.amen.dto.ReplyDto;
import com.saname.amen.exception.CustomErrorException;
import com.saname.amen.model.Post;
import com.saname.amen.model.Reply;
import com.saname.amen.repository.PostRepository;
import com.saname.amen.repository.ReplyRepository;
import com.saname.amen.security.UserDetailsImpl;
import com.saname.amen.service.Postservice;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class PostRestController {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final Postservice postservice;


    public PostRestController(Postservice postservice,PostRepository postRepository, ReplyRepository replyRepository){
        this.postRepository = postRepository;
        this.replyRepository =replyRepository;
        this.postservice = postservice;
    }


//    List<MyBlog> listblog = myBlogRepository.findAll();
//    Comparator<MyBlog> compare = Comparator
//            .comparing(MyBlog::getCreatedAt).reversed();
//    List<MyBlog> sortedList = listblog.stream()
//            .sorted(compare)
//            .collect(Collectors.toList());
//        return sortedList;
    //메인화면 리스트 값 출력 (GET)

    @GetMapping("/post")
    public List<Post> getPostList(){
        return postRepository.findAllByOrderByCreatedAtDesc();
    }



    // 포스트 작성 (POST) -id - contents title
    @PostMapping("/post")
    public Long savePost(@RequestBody PostDto postDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails==null){
            throw new CustomErrorException("로그인하십쇼");
        }else {
            String username = userDetails.getUsername();
            Post post = new Post(postDto,username);
            postRepository.save(post);
            return post.getId();
        }

    }

    //댓글 작성(POST) -id -post -rplContens
    @PostMapping("post/detail/reply/{postId}")// 댓글 입력한 사람 아이디 필요함
    public Long saveReply(@PathVariable Long postId, @RequestBody ReplyDto replyDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails==null){
            throw new CustomErrorException("로그인하십쇼");
        }else{
            postservice.saveRpl(postId,replyDto.getContents(),userDetails.getUsername());
            return postId;
        }
    }


    // 포스트 상세 페이지 가져오기 (GET) -Post

    @GetMapping("/post/detail/{id}")
    public Post detail(@PathVariable Long id){
        return postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("ddddd"));
    }




    //포스트 삭제 (delete)
    @DeleteMapping("/post/delete")
    public void deletePost(@RequestParam Long id){
        postservice.deletePost(id);
    }


    //댓글 삭제 (delete)
    @DeleteMapping("/post/delete/{id}")
    public void deleteRpl(@PathVariable Long id){
        Reply rpl =  replyRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("아이디가 없는데 삭제를 어떻게 합니까..."));
        replyRepository.delete(rpl);
    }

}
