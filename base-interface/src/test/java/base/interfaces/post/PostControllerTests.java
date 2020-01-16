package base.interfaces.post;

import base.application.post.CachedPostProvider;
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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.NumberUtils;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostControllerTests extends BaseTestControllerSupport {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CachedPostReadCountProvider cachedReadCountProvider;

    private Member savedMember;

    @BeforeEach
    public void setUp() {
        EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3, 5)
                .collectionSizeRange(1, 3)
                .build();

        Member member = builder.nextObject(Member.class, "memberId");
        savedMember = memberRepository.save(member);
    }

    @Test
    @WithMockUser
    @DisplayName("게시글 생성 테스트")
    public void createPostTest() throws Exception {
        //given
        String url = "/posts";
        Post post = Post.builder()
                .member(savedMember)
                .boardType(BoardType.FREE)
                .title("제목")
                .contents("내용")
                .build();

        //when, then
        postResource(url, post)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(JSON_UTF8_MEDIA_TYPE))
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.contents").value(post.getContents()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    @DisplayName("게시글 변경 테스트")
    public void updatePostTest() throws Exception {
        //given
        Post post = Post.builder()
                .member(savedMember)
                .boardType(BoardType.FREE)
                .title("제목")
                .contents("내용")
                .build();

        Post savedPost = postRepository.save(post);

        String url = "/posts/" + savedPost.getPostId();

        //when, then
        putResource(url, post)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @WithMockUser
    @DisplayName("게시글 및 댓글 리스트 조인 조회 테스트")
    public void findPostWithCommentTest() throws Exception {
        //given
        Post post = Post.builder()
                .member(savedMember)
                .boardType(BoardType.FREE)
                .title("제목")
                .contents("내용")
                .build();

        Post savedPost = postRepository.save(post);

        Comment comment = Comment.builder().member(savedMember)
                .postId(savedPost.getPostId())
                .contents("댓글")
                .build();

        commentRepository.save(comment);
        String url = "/posts/" + savedPost.getPostId();

        //when, then
        mockMvc.perform(get(url)
                .param("page", "0")
                .param("size", "5")
                .param("sort", "postId")
                .contentType(JSON_UTF8_MEDIA_TYPE)
                .accept(JSON_UTF8_MEDIA_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$.postId").value(post.getPostId()))
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.contents").value(post.getContents()))
                .andExpect(jsonPath("$.comments", hasSize(1)))
                .andExpect(jsonPath("$.comments[0].contents", is(comment.getContents())))

                .andExpect(jsonPath("$.member.memberId",
                        is(NumberUtils.convertNumberToTargetClass(savedMember.getMemberId(), Integer.class))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    @DisplayName("게시글 삭제 테스트")
    public void deletePostTest() throws Exception {
        //given
        Post post = Post.builder()
                .member(savedMember)
                .boardType(BoardType.FREE)
                .title("제목")
                .contents("내용")
                .build();

        Post savedPost = postRepository.save(post);
        String url = "/posts/" + savedPost.getPostId();

        //when, then
        deleteResource(url, null)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    @DisplayName("조회수 캐쉬 테스트")
    public void updateCacheTest() throws Exception {
        //given
        Post post = Post.builder()
                .member(savedMember)
                .boardType(BoardType.FREE)
                .title("제목")
                .contents("내용")
                .build();

        Post savedPost = postRepository.save(post);
        long postId = savedPost.getPostId();
        String url = "/posts/" + postId;

        //when, then
        for(int i=0; i<100; i++){
            mockMvc.perform(get(url)
                    .contentType(JSON_UTF8_MEDIA_TYPE)
                    .accept(JSON_UTF8_MEDIA_TYPE))
                    .andExpect(status().isOk());
        }

        Optional<Post> getPost = postRepository.findById(postId);

        assertThat(cachedReadCountProvider.get(postId)).isPresent();
        assertThat(cachedReadCountProvider.get(postId).get().getCount()).isEqualTo(100);
        assertThat(getPost.get().getReadCount()).isEqualTo(100);
    }
}









