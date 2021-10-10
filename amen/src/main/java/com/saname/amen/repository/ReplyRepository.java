package com.saname.amen.repository;

import com.saname.amen.model.Post;
import com.saname.amen.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    void deleteAllByPost(Post post);


}
