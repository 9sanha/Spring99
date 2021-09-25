package com.saname.myblog.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class MyBlog extends Timestamped{
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String nickname;

    public  MyBlog(MyBlogDto myBlogDto){
        this.title=myBlogDto.getTitle();
        this.contents=myBlogDto.getContents();
        this.nickname=myBlogDto.getNickname();

    }

}
