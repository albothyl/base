package base.application.post;

import base.domain.post.cache.CachedPost;
import base.domain.post.entity.Post;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = CachedPostProvider.class)
public class CachedPostProviderTests {

    @Autowired
    private CachedPostProvider cachedPostProvider;

    @Test
    @Order(1)
    public void get_expected_null_test(){
        CachedPost cachedPost = cachedPostProvider.get(1);
        assertThat(cachedPost).isNull();
    }

    @Test
    @Order(2)
    public void get_and_set_test(){
        Post post = Post.builder()
                .postId(1l)
                .contents("asd")
                .build();
        cachedPostProvider.set(post.getPostId(), post);
        CachedPost cachedPost = cachedPostProvider.get(post.getPostId());

        assertThat(cachedPost).isNotNull();
        assertThat(cachedPost.getPost()).isEqualTo(post);
    }

    @Test
    @Order(3)
    public void remove_test(){
        Post post = Post.builder()
                .postId(1l)
                .contents("asd")
                .build();
        cachedPostProvider.set(post.getPostId(), post);
        cachedPostProvider.remove(post.getPostId());
        CachedPost getPost = cachedPostProvider.get(post.getPostId());

        assertThat(getPost).isNull();
    }
}
