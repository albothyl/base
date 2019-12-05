package base.domain.post.entity;

import base.domain.member.entity.Member;
import base.domain.post.constants.BoardType;
import base.domain.support.entity.CreatedAndModifiedEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "posts", schema = "base")
public class Post extends CreatedAndModifiedEntity {

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

    private int readCount;


    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "postId")
    private final List<Comment> comments = new ArrayList<>();

}