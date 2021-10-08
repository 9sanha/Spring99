package com.saname.amen.model;


import com.saname.amen.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


// id, contents username reply EnumUser
@NoArgsConstructor
@Getter
@Entity
public class Post extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

//    @Column(nullable = false, unique = true)
//    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @OrderBy("createdAt desc")
    @OneToMany(mappedBy = "post")
    @Column(unique = true)
    private final List<Reply>  reply = new ArrayList<>();

    public Post(PostDto postDto){
//        this.username="아멘";
        this.contents=postDto.getContents();
        this.title=postDto.getTitle();
    }
    public void addReply(Reply reply){
        this.reply.add(reply);

    }


}
