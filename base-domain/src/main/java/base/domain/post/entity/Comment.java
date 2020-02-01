package base.domain.post.entity;

import base.domain.member.entity.Member;
import base.domain.support.entity.CreatedAndModifiedEntity;
import lombok.*;

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

    @JoinColumn(name = "postId", nullable = false)
    private Long postId;

    private String contents;

    public void update(String contents){
        this.contents = contents;
    }
}