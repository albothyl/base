package base.domain.post.entity;

import base.domain.member.entity.Member;
import base.domain.support.entity.CreatedAndModifiedEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comments", schema = "base")
public class Comment extends CreatedAndModifiedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "postId", nullable = false)
    //테스트 및 확인필요
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    private String contents;

}