package com.saname.sanblog.service;

import com.saname.sanblog.dto.PostDto;
import com.saname.sanblog.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PostService {
    private final PostRepository postRepository;
    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Boolean filter(PostDto postDto) {
        String postInfo = postDto.getNickname() + postDto.getTitle() + postDto.getContents();
        System.out.println(postInfo);
        List<String> scrptList = new ArrayList<>();
        scrptList.add("<script>");
        scrptList.add("<javascript>");
        scrptList.add("<vbscript>");
        scrptList.add("onerror");
        System.out.println(scrptList);
        Iterator<String> var4 = scrptList.iterator();

        String i;
        do {
            if (!var4.hasNext()) {
                return true;
            }

            i = var4.next();
            System.out.println(i);
        } while(!postInfo.contains(i));

        return false;
    }


}
