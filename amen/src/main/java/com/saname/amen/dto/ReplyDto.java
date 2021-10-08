package com.saname.amen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyDto {

    private String contents;

    ReplyDto(String contents){
        this.contents=contents;
    }
}
