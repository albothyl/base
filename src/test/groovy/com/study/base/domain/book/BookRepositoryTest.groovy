package com.study.base.domain.book

import com.study.base.domain.book.entity.Book
import com.study.base.domain.member.MemberRepository
import com.study.base.domain.member.entity.Member
import com.study.base.domain.support.PasswordEncoderUtils
import org.assertj.core.util.Lists
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class BookRepositoryTest extends Specification{

    @Autowired
    BookRepository bookRepository

    @Autowired
    MemberRepository memberRepository

    def setup() {
        bookRepository.deleteAll()
        memberRepository.deleteAll()
    }

    def cleanup() {
        bookRepository.deleteAll()
        memberRepository.deleteAll()
    }

    def "book innerJoin with member test"() {
        given:

        List<Member> memberList = Lists.newArrayList()
        List<Book> bookList = Lists.newArrayList()

        for(int i = 1; i <10; i++) {
            def password = PasswordEncoderUtils.passwordEncode("user"+i)

            def member = Member.builder()
                    .memberPassword(password)
                    .memberName("user"+i)
                    .memberEmail("user"+i+"@naver.com")
                    .memberAge(20)
                    .memberSex("MALE")
                    .memberAddress("korea")
                    .memberPhoneNumber("010-1234-123"+i)
                    .memberGrade("GOLD")
                    .roles(["USER"])
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build()

            memberList.add(member)

            for(int j = 0; j <10; j++) {
                def book = Book.builder()
                    .bookName("책"+j)
                    .member(member)
                    .build()

                bookList.add(book)
            }
        }

        memberRepository.saveAll(memberList)
        bookRepository.saveAll(bookList)

        when:
        def result = bookRepository.findAllInnerJoinWithMembers()

        then:

        result.size() == 90

        for(Book book : result) {
            assert book.member != null
        }
    }
}
