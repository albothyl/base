package com.study.base.domain.book;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.base.domain.book.entity.Book;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.study.base.domain.book.entity.QBook.book;
import static com.study.base.domain.member.entity.QMember.member;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Book> findAllInnerJoinWithMembers() {
        return queryFactory.selectFrom(book)
                .innerJoin(book.member, member)
                .fetchJoin()
                .fetch();
    }
}
