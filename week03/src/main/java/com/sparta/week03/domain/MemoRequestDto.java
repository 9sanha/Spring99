package com.sparta.week03.domain;


import lombok.Getter;

@Getter
public class MemoRequestDto {

    private String username;
    private String contents;

    public void MemoRequstDto(MemoRequestDto memoRequestDto){
        this.username = memoRequestDto.getUsername();
        this.contents = memoRequestDto.getContents();
    }
}
