package base.domain.post;

import base.domain.post.entity.Post;
import java.util.List;

public interface PostRepositoryCustom  {
    List<Post> findByIdWithComments(Long postId);
}
