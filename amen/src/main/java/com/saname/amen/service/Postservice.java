package com.saname.amen.service;

import com.saname.amen.dto.ReplyDto;
import com.saname.amen.model.Post;
import com.saname.amen.model.Reply;
import com.saname.amen.repository.PostRepository;
import com.saname.amen.repository.ReplyRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class Postservice {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    public Postservice(PostRepository postRepository,ReplyRepository replyRepository){
        this.postRepository=postRepository;
        this.replyRepository=replyRepository;
    }

    //포스트 삭제
    public void deletePost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("ddddd"));
        replyRepository.deleteAllByPost(post);
        postRepository.delete(post);
    }

    // 댓글 저장
    @Transactional
    public void saveRpl(Long postId, String contents, String username) {

        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("해당 포스트를 찾을 수 없습니다."));

        Reply reply = new Reply(contents,post,username);

        // post에 reply 업데이트
        post.addReply(reply);
        replyRepository.save(reply);
    }

    // 댓글 업데이트
    @Transactional
    public void updateReply(ReplyDto replyDto) {
        Reply reply = replyRepository.findById(replyDto.getId())
                .orElseThrow(()->new IllegalArgumentException("d"));
        reply.updateContents(replyDto.getContents());
    }
}
