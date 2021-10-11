package com.saname.amen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saname.amen.repository.PostRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Reply extends Timestamped{


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

                          //영속성 컨텍스트랑 관련이씅ㅁ 연관관계에 있는 애를 한번에 데려오느냐 나중에 데려오느냐ㅑ
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "post_Id")
    private Post post;


    public Reply(String contents, Post post, String username) {
        this.contents=contents;
        this.post=post;
        this.username=username;
    }

    public void updateContents(String contents) {
        this.contents=contents;
    }
}
