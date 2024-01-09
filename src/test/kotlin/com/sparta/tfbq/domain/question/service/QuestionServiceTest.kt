package com.sparta.tfbq.domain.question.service

import com.sparta.tfbq.domain.member.model.Member
import com.sparta.tfbq.domain.member.model.MemberRole
import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.domain.question.dto.request.AddQuestionRequest
import com.sparta.tfbq.global.exception.WrongRoleException
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows

@SpringBootTest
@Transactional
class QuestionServiceTest {
    @Autowired
    lateinit var service: QuestionService

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun addQuestion() {
        // given
        val student = Member("John", "john@gmail.com", "존", MemberRole.STUDENT, "1234")
        memberRepository.save(student)
        val tutor = Member("Jack", "jack@gmail.com", "튜터 잭", MemberRole.TUTOR, "12345")
        memberRepository.save(tutor)

        // when
        val request = AddQuestionRequest(2L, "질문 제목 예시", "질문 내용 예시", false)
        service.addQuestion(student.id!!, request)
        assertThrows(WrongRoleException::class.java) {service.addQuestion(tutor.id!!, request)}

        // then
        assertThat(student.questions.size).isEqualTo(1)
        assertThat(student.questions[0].title).isEqualTo("질문 제목 예시")
    }
}