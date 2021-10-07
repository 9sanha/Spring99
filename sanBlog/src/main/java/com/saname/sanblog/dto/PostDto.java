package com.saname.sanblog.dto;

public class PostDto {
    private String title;
    private String contents;
    private String nickname;

    public PostDto() {
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
