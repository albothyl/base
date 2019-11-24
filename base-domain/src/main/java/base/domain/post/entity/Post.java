package base.domain.post.entity;

import base.domain.member.entity.Member;
import base.domain.post.constants.BoardType;
import base.domain.support.entity.CreatedAndModifiedEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "posts", schema = "base")
public class Post extends CreatedAndModifiedEntity {

    @Builder
    public Post(Member member, BoardType boardType, String title, String contents, int hit) {
        this.member = member;
        this.boardType = boardType;
        this.title = title;
        this.contents = contents;
        this.hit = hit;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    private String title;

    private String contents;

    private int hit;

    //단방향으로 설정
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="postId")
    private Collection<Comment> comments = new ArrayList<>();

}