package base.domain.post;

import base.domain.member.MemberRepository;
import base.domain.member.entity.Member;
import base.domain.post.constants.BoardType;
import base.domain.post.entity.Comment;
import base.domain.post.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PostRepositoryTests {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("post와 member 대한 정보를 저장한다.")
    @Transactional
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
    @DisplayName("comment를 10개 저장하고, 모든 정보를 가져온다.")
    public void fetchJoinTest() {

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
        List<Comment> savedComment = commentRepository.saveAll(commentList);

        List<Post> fetchJoinPost = postRepository.findAllFetchJoin();


        //then
        assertThat(savedMember).isNotNull();
        assertThat(savedPost).isNotNull();
        assertThat(savedComment).hasSize(10);

        assertThat(fetchJoinPost).isNotNull();
        assertThat(fetchJoinPost).hasSize(1);
        assertThat(fetchJoinPost.get(0).getPostId()).isEqualTo(savedPost.getPostId());
        assertThat(fetchJoinPost.get(0).getMember().getMemberId()).isEqualTo(savedMember.getMemberId());
        assertThat(fetchJoinPost.get(0).getComments()).hasSize(10);

    }
}
