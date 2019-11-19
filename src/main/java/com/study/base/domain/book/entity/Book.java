package com.study.base.domain.book.entity;

import com.study.base.domain.member.entity.Member;
import com.study.base.domain.support.entity.CreatedAndModifiedEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "books", schema = "base")
public class Book extends CreatedAndModifiedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String bookName;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member memberId;
}
