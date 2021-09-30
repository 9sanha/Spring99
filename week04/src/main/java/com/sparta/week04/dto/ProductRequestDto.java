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
}