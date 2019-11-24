package base.domain.post;

import base.domain.member.entity.Member;
import base.domain.support.entity.CreatedAndModifiedEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts", schema = "base")
public class Post extends CreatedAndModifiedEntity {

    @Builder
    public Post(Member member, Board board, String title, String contents) {
        this.member = member;
        this.board = board;
        this.title = title;
        this.contents = contents;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "boardId", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    private String title;

    private String contents;

    @OneToMany( mappedBy = "post", cascade = CascadeType.ALL)
    private Collection<Comment> comments = new ArrayList<>();

}