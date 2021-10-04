package com.sparta.week04.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Setter
@Getter
public class ProductMypriceRequestDto {
    private int myprice;


    public ProductMypriceRequestDto(int myprice) {
        this.myprice=myprice;
    }
}