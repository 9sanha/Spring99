package com.saname.amen.dto;

import com.saname.amen.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {

    String title;
    String contents;


    public PostDto(Post post) {

        this.title= post.getTitle();
        this.contents= post.getContents();
    }
}
