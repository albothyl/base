package base.domain;

import base.domain.member.MemberRepository;
import base.domain.member.entity.Member;
import base.domain.post.CommentRepository;
import base.domain.post.PostRepository;
import base.domain.post.constants.BoardType;
import base.domain.post.entity.Comment;
import base.domain.post.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTests {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;


    @Test
    @Order(1)
    @DisplayName("post와 comment에 대한 정보를 저장한다.")
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
        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getContents()).isEqualTo(comment.getContents());
        assertThat(savedComment.getPost().getTitle()).isEqualTo(savedPost.getTitle());
        assertThat(savedComment.getMember().getMemberName()).isEqualTo(member.getMemberName());
    }

    @Test
    @Order(2)
    @DisplayName("comment를 10개 저장하고, 모든 정보를 가져온다.")
    public void fetchJoinTest() {



    }
}
