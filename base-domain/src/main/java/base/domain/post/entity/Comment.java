package base.domain.post.entity;

import base.domain.member.entity.Member;
import base.domain.support.entity.CreatedAndModifiedEntity;
import lombok.*;
import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Post post;

    private String contents;

}