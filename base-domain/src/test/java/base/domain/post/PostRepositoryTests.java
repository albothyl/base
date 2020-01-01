package base.domain.post;

import base.domain.member.repository.MemberRepository;
import base.domain.member.entity.Member;
import base.domain.post.constants.BoardType;
import base.domain.post.entity.Comment;
import base.domain.post.entity.Post;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.FieldPredicates;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostRepositoryTests {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostRepository postRepository;


    @Test
    @Transactional
    @Order(1)
    public void saveTest() {
        //given
        EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3,5)
                .collectionSizeRange(1,3)
                .build();

        Member member = builder.nextObject(Member.class, "memberId");

        Post post = Post.builder()
                .boardType(BoardType.NOTICE)
                .member(member)
                .title("공지사항")
                .contents("내용")
                .build();


        //when
        Member savedMember = memberRepository.save(member);
        Post savedPost = postRepository.save(post);

        //then
        assertThat(savedMember).isNotNull();
        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(savedPost.getContents()).isEqualTo(post.getContents());
        assertThat(savedPost.getMember().getMemberName()).isEqualTo(member.getMemberName());
    }

    @Test
    @Order(2)
    public void customPostRepositoryTest() {

        //given
        EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3,5)
                .collectionSizeRange(1,3)
                .build();

        Member member = builder.nextObject(Member.class, "memberId");

        Post post = Post.builder()
                .boardType(BoardType.NOTICE)
                .member(member)
                .title("공지사항")
                .contents("내용")
                .build();

        //when
        Member savedMember = memberRepository.save(member);
        Post savedPost = postRepository.save(post);

        for(int i=0; i<10; i++){
            Comment comment  = Comment.builder()
                    .member(member)
                    .postId(savedPost.getPostId())
                    .contents("댓글" + i)
                    .build();
            commentRepository.save(comment);
        }

        Post fetchJoinPost = postRepository.findByIdWithComments(post.getPostId());
        //then
        assertThat(savedMember).isNotNull();
        assertThat(savedPost).isNotNull();
        assertThat(fetchJoinPost).isNotNull();
        assertThat(fetchJoinPost.getPostId()).isEqualTo(savedPost.getPostId());
        assertThat(fetchJoinPost.getMember().getMemberId()).isEqualTo(savedMember.getMemberId());
        assertThat(fetchJoinPost.getComments()).hasSize(10);
    }


    @Test
    public void customCommentRepositoryTest(){
        //given
        EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .excludeField(FieldPredicates.named("memberId"))
                .stringLengthRange(3,5)
                .collectionSizeRange(1,3)
                .build();

        Member member = builder.nextObject(Member.class);

        Post post = Post.builder()
                .boardType(BoardType.NOTICE)
                .member(member)
                .title("공지사항")
                .contents("내용")
                .build();

        Comment comment  = Comment.builder()
                .member(member)
                .postId(post.getPostId())
                .contents("댓글")
                .build();

        Member savedMember = memberRepository.save(member);
        Post savedPost = postRepository.save(post);
        Comment savedComment = commentRepository.save(comment);

        assertThat(savedMember).isNotNull();
        assertThat(savedPost).isNotNull();
        assertThat(savedComment).isNotNull();
    }
}
