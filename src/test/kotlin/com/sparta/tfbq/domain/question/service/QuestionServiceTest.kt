package com.sparta.tfbq.domain.question.service

import com.sparta.tfbq.domain.member.model.Member
import com.sparta.tfbq.domain.member.model.MemberRole
import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.domain.question.dto.request.AddQuestionRequest
import com.sparta.tfbq.global.exception.ModelNotFoundException
import com.sparta.tfbq.global.exception.WrongRoleException
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.springframework.data.repository.findByIdOrNull
import org.springframework.ui.Model

@SpringBootTest
@Transactional
class QuestionServiceTest {
    @Autowired
    lateinit var service: QuestionService

    @Autowired
    lateinit var memberRepository: MemberRepository

    @BeforeEach
    fun test() {
        val student = Member("John", "john@gmail.com", "존", MemberRole.STUDENT, "1234")
        memberRepository.save(student)
        val tutor = Member("Jack", "jack@gmail.com", "튜터 잭", MemberRole.TUTOR, "12345")
        memberRepository.save(tutor)
    }

    @Test
    fun addQuestion() {
        // given
        val student = memberRepository.findByIdOrNull(1L) ?: throw ModelNotFoundException("")
        val tutor = memberRepository.findByIdOrNull(2L) ?: throw ModelNotFoundException("")

        // when
        val requestOfStudent = AddQuestionRequest(student.id!!, tutor.id!!, "질문 제목 예시", "질문 내용 예시", false)
        service.addQuestion(requestOfStudent)

        val requestOfTutor = AddQuestionRequest(tutor.id!!, tutor.id!!, "질문 제목 예시", "질문 내용 예시", false)
        assertThrows(WrongRoleException::class.java) { service.addQuestion(requestOfTutor) }

        // then
        assertThat(student.questions.size).isEqualTo(1)
        assertThat(student.questions[0].title).isEqualTo("질문 제목 예시")
    }
}