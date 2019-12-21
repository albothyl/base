package base.domain.post;

import base.domain.post.entity.Post;

public interface PostRepositoryCustom  {
    Post findByIdWithComments(Long postId);
}
