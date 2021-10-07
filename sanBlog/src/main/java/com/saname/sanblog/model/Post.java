package com.saname.sanblog.model;

import com.saname.sanblog.dto.PostDto;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class Post extends Timestamped{
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Id
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String nickname;

    public Post() {}
    public Post(PostDto postDto) {
        this.title = postDto.getTitle();
        this.contents = postDto.getContents();
        this.nickname = postDto.getNickname();
    }


    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContents() {
        return this.contents;
    }

    public String getNickname() {
        return this.nickname;
    }



}
