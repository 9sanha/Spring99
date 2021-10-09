//package com.saname.amen.controller;
//
//import com.saname.amen.dto.PostDto;
//import com.saname.amen.dto.ReplyDto;
//import com.saname.amen.exception.CustomErrorException;
//import com.saname.amen.model.Post;
//import com.saname.amen.repository.PostRepository;
//import com.saname.amen.security.UserDetailsImpl;
//import com.saname.amen.service.Postservice;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@Controller
//public class PostController {
//
//    private final PostRepository postRepository;
//    private final Postservice postservice;
//
//    public PostController(PostRepository postRepository, Postservice postservice) {
//        this.postRepository = postRepository;
//        this.postservice = postservice;
//    }
//
//
//    // 포스트 작성 (POST) -id - contents title
//    @PostMapping("/post")
//    public String savePost(@RequestBody PostDto postDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        if (userDetails==null){
//            throw new CustomErrorException("로그인하십쇼");
//        }else {
//            String username = userDetails.getUsername();
//            Post post = new Post(postDto,username);
//            postRepository.save(post);
//            return "redirect:/";
//        }
//
//    }
//
//    //댓글 작성(POST) -id -post -rplContens
//    @PostMapping("post/detail/reply/{postId}")// 댓글 입력한 사람 아이디 필요함
//    public String saveReply(@PathVariable Long postId, @RequestBody ReplyDto replyDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        if (userDetails==null){
//            throw new CustomErrorException("로그인하십쇼");
//        }else{
//            postservice.saveRpl(postId,replyDto.getContents(),userDetails.getUsername());
//            return "redirect:/";
//        }
//    }
//}
