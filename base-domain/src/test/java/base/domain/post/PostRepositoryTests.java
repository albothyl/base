package base.domain.post;

import base.domain.member.MemberRepository;
import base.domain.member.entity.Member;
import base.domain.post.constants.BoardType;
import base.domain.post.entity.Comment;
import base.domain.post.entity.Post;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Member member = Member.builder()
                .memberPassword("")
                .memberName("admin")
                .memberEmail("admin@naver.com")
                .memberAge(20)
                .memberSex("MALE")
                .memberAddress("korea")
                .memberPhoneNumber("010-1234-1234")
                .memberGrade("GOLD")
                .roles(Arrays.asList("ADMIN"))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

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
        Member member = Member.builder()
                .memberPassword("")
                .memberName("admin")
                .memberEmail("admin@naver.com")
                .memberAge(20)
                .memberSex("MALE")
                .memberAddress("korea")
                .memberPhoneNumber("010-1234-1234")
                .memberGrade("GOLD")
                .roles(Arrays.asList("ADMIN"))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

        EnhancedRandom.random(Member.class);
        Post post = Post.builder()
                .boardType(BoardType.NOTICE)
                .member(member)
                .title("공지사항")
                .contents("내용")
                .build();


        List<Comment> commentList = new ArrayList<>();
        for(int i=0; i<10; i++){
            Comment comment  = Comment.builder()
                    .member(member)
                    .post(post)
                    .contents("댓글" + i)
                    .build();
            commentList.add(comment);
        }
        //when
        Member savedMember = memberRepository.save(member);
        Post savedPost = postRepository.save(post);

        List<Post> fetchJoinPost = postRepository.findAllLeftJoinWithMember();

        //then
        assertThat(savedMember).isNotNull();
        assertThat(savedPost).isNotNull();
        assertThat(fetchJoinPost).isNotNull();
        assertThat(fetchJoinPost).hasSize(1);
        assertThat(fetchJoinPost.get(0).getPostId()).isEqualTo(savedPost.getPostId());
        assertThat(fetchJoinPost.get(0).getMember().getMemberId()).isEqualTo(savedMember.getMemberId());
    }


    @Test
    public void customCommentRepositoryTest(){
        //given
        Member member = Member.builder()
                .memberPassword("")
                .memberName("admin")
                .memberEmail("admin@naver.com")
                .memberAge(20)
                .memberSex("MALE")
                .memberAddress("korea")
                .memberPhoneNumber("010-1234-1234")
                .memberGrade("GOLD")
                .roles(Arrays.asList("ADMIN"))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

        Post post = Post.builder()
                .boardType(BoardType.NOTICE)
                .member(member)
                .title("공지사항")
                .contents("내용")
                .build();

        Comment comment  = Comment.builder()
                .member(member)
                .post(post)
                .contents("댓글")
                .build();

        Member savedMember = memberRepository.save(member);
        Post savedPost = postRepository.save(post);
        Comment savedComment = commentRepository.save(comment);



        Pageable pageable =  PageRequest.of(0, 10);
        Page<Comment> commentPage = commentRepository.findByPostId(savedPost.getPostId(), pageable);
        List<Comment> list = commentPage.get().collect(Collectors.toList());
        Optional<Comment> optionalComment =  commentRepository.findByCommentIdAndPostId(savedComment.getCommentId(), savedPost.getPostId());


        assertThat(savedMember).isNotNull();
        assertThat(savedPost).isNotNull();
        assertThat(savedComment).isNotNull();
        assertThat(commentPage).isNotNull();

        assertThat(list).hasSize(1);
        assertThat(optionalComment).isPresent();
        assertThat(optionalComment.get().getCommentId()).isEqualTo(savedComment.getCommentId());

    }
}
