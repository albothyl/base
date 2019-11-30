package base.domain.post.entity;

import base.domain.member.entity.Member;
import base.domain.post.constants.BoardType;
import base.domain.support.entity.CreatedAndModifiedEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "posts", schema = "base")
//클래스 이름 변경 확인 필
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
    //hit 컬럼 명 readCount 변경
    private int hit;

    // 단방향으로 post에서 재설정
    //삭제 양방향 제거
//    @ToString.Exclude
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="postId")
//    private Collection<Comment> comments = new ArrayList<>();

}