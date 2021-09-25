package com.saname.myblog.sevice;

import com.saname.myblog.models.MyBlog;
import com.saname.myblog.models.MyBlogDto;
import com.saname.myblog.models.MyBlogRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@Service // 서비스임을 선언합니다.
public class MyBlogService {

    private final MyBlogRepository myBlogRepository;

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Boolean filter(MyBlogDto requestDto) {
        String postInfo = requestDto.getNickname()+requestDto.getTitle()+requestDto.getContents();
        System.out.println(postInfo);
        List<String> scrptList = new ArrayList<>();
        scrptList.add("<script>");
        scrptList.add("<javascript>");
        scrptList.add("<vbscript>");
        scrptList.add("onerror");
        System.out.println(scrptList);
        for (String i:scrptList){
            System.out.println(i);
            if(postInfo.contains(i)){

                return false;
            }
        }
        return true;

    }
}

