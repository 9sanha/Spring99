package com.saname.amen.service;

import com.saname.amen.model.Post;
import com.saname.amen.model.Reply;
import com.saname.amen.repository.PostRepository;
import com.saname.amen.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.StringContent;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class Postservice {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    public Postservice(PostRepository postRepository,ReplyRepository replyRepository){
        this.postRepository=postRepository;
        this.replyRepository=replyRepository;
    }

    @Transactional
    public void saveRpl(Long postId, String contents){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("eeee"));
        Reply reply = new Reply(contents,post);
        post.addReply(reply);
        replyRepository.save(reply);


    }

}
