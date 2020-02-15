package base.domain.post;

import base.domain.post.entity.Post;

public interface PostRepositoryCustom  {

    @Deprecated
    Post findByIdWithComments(Long postId);
}
