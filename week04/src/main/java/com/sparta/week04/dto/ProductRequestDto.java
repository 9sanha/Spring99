package com.sparta.week04.dto;


import lombok.Getter;


@Getter
public class ProductRequestDto {
    private String title;
    private String link;
    private String image;
    private String userId;
    private int lprice;
    private int myprice;

    public ProductRequestDto(String title,String image,String link,int lprice){
        this.title=title;
        this.image =image;
        this.link = link;
        this.lprice=lprice;
    }
}