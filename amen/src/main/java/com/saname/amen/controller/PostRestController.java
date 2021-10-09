package com.saname.amen.controller;

import com.saname.amen.dto.PostDto;
import com.saname.amen.dto.ReplyDto;
import com.saname.amen.model.Post;
import com.saname.amen.model.Reply;
import com.saname.amen.repository.PostRepository;
import com.saname.amen.repository.ReplyRepository;
import com.saname.amen.security.UserDetailsImpl;
import com.saname.amen.service.Postservice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    //작성된 포스트 리스트 전달
    @GetMapping("/post")
    public List<Post> getPostList(){
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // 포스트 작성 (POST) -id - contents title
    @PostMapping("/post")
    public Long savePost(@RequestBody PostDto postDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails == null){
            return -1L;
//            throw new CustomErrorException("로그인하십쇼");
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
            return -1L;
//            throw new CustomErrorException("로그인하십쇼");
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

    //댓글 업데이트
    @PutMapping("/post/update")
    public void updateReply(@RequestBody ReplyDto replyDto){

        postservice.updateReply(replyDto);

    }


    //포스트 삭제 (delete)
    @DeleteMapping("/post/delete")
    public void deletePost(@RequestParam Long id){
        postservice.deletePost(id);
    }

    //댓글 삭제 (delete)
    @DeleteMapping("/post/delete/{id}")
    public Long deleteRpl(@PathVariable Long id){
        Reply rpl =  replyRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("아이디가 없는데 삭제를 어떻게 합니까..."));
        Long postId = rpl.getPost().getId();
        replyRepository.delete(rpl);
        return postId;
    }
}
