package base.domain.post.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardType {

    NOTICE("공지사항"),
    FREE("자유게시판");

    private String description;
}
