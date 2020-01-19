package base.interfaces.post;

import base.domain.member.repository.MemberRepository;
import base.domain.member.entity.Member;
import base.domain.post.CommentRepository;
import base.domain.post.PostRepository;
import base.domain.post.constants.BoardType;
import base.domain.post.entity.Comment;
import base.domain.post.entity.Post;
import base.support.BaseTestControllerSupport;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentControllerTests extends BaseTestControllerSupport {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    private Member savedMember;
    private Post savedPost;


    @BeforeEach
    public void setUp() {
        EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3, 5)
                .collectionSizeRange(1, 3)
                .build();

        Member member = builder.nextObject(Member.class, "memberId");

        savedMember = memberRepository.save(member);

        Post post = Post.builder()
                .boardType(BoardType.FREE)
                .title("제목")
                .contents("내용")
                .member(savedMember)
                .build();

        savedPost = postRepository.save(post);
    }

    @Test
    @WithMockUser
    @DisplayName("댓글 생성 테스트")
    public void createCommentTest() throws Exception {
        //given
        String url = "/posts/comment";
        Comment comment = Comment.builder()
                .contents("댓글생성")
                .postId(savedPost.getPostId())
                .member(savedMember)
                .build();

        //when, then
        postResource(url, comment)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(JSON_UTF8_MEDIA_TYPE))
                .andExpect(jsonPath("$.contents").value(comment.getContents()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    @DisplayName("댓글 변경 테스트")
    public void updateCommentTest() throws Exception {
        //given
        Comment comment = Comment.builder()
                .contents("댓글생성")
                .postId(savedPost.getPostId())
                .member(savedMember)
                .build();

        Comment savedComment = commentRepository.save(comment);
        String url = "/posts/comment/" + savedComment.getCommentId();


        //when, then
        patchResource(url, savedComment)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    @DisplayName("댓글 삭제 테스트")
    public void deleteCommentTest() throws Exception {
        //given
        Comment comment = Comment.builder()
                .contents("댓글생성")
                .postId(savedPost.getPostId())
                .member(savedMember)
                .build();

        Comment savedComment = commentRepository.save(comment);
        String url = "/posts/comment/" + savedComment.getCommentId();


        //when, then
        deleteResource(url, null)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
