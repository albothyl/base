package base.domain.board.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board", schema = "base")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String title;
    private String content;

    @Column(insertable = false, updatable = false, columnDefinition = "date default CURRENT_TIMESTAMP")
    private Date createDate;
}
