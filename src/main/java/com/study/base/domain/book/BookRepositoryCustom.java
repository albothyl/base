package com.study.base.domain.book;

import com.study.base.domain.book.entity.Book;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> findAllInnerJoinWithMembers();
}
