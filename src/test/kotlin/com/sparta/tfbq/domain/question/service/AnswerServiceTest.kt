package com.sparta.tfbq.domain.question.service

import com.sparta.tfbq.domain.answer.dto.request.AddAnswerRequest
import com.sparta.tfbq.domain.answer.service.AnswerService
import com.sparta.tfbq.domain.member.Member
import com.sparta.tfbq.domain.member.model.MemberRole
import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.domain.question.dto.request.AddQuestionRequest
import com.sparta.tfbq.domain.question.repository.QuestionRepository
import com.sparta.tfbq.global.exception.ModelNotFoundException
import com.sparta.tfbq.global.exception.WrongRoleException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class AnswerServiceTest {
    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var questionRepository: QuestionRepository

    @Autowired
    lateinit var questionService: QuestionService

    @Autowired
    lateinit var answerService: AnswerService

    @BeforeEach
    fun test() {
        val student = Member("John", "john@gmail.com", "존", MemberRole.STUDENT, "1234")
        memberRepository.save(student)
        val tutor = Member("Jack", "jack@gmail.com", "튜터 잭", MemberRole.TUTOR, "12345")
        memberRepository.save(tutor)
        val request = AddQuestionRequest(student.id!!, tutor.id!!, "질문 제목", "질문 내용", false)
        questionService.addQuestion(request)
    }

    @Test
    fun addAnswer() {
        // given
        val student = memberRepository.findByIdOrNull(1L) ?: throw ModelNotFoundException("")
        val tutor = memberRepository.findByIdOrNull(2L) ?: throw ModelNotFoundException("")
        val question = questionRepository.findByIdOrNull(1L) ?: throw ModelNotFoundException("")

        // when (정상 처리)
        val request1 = AddAnswerRequest(tutor.id!!, "답변 내용 1")
        val request2 = AddAnswerRequest(tutor.id!!, "답변 내용 2")
        answerService.addAnswer(question.id!!, request1)
        answerService.addAnswer(question.id!!, request2)

        // then (정상 처리)
        assertThat(question.answers.size).isEqualTo(2)
        assertThat(question.answers[0].content).isEqualTo("답변 내용 1")

        // when (예외: 학생이 답변을 등록하는 경우)
        val requestOfStudent = AddAnswerRequest(student.id!!, "답변 내용 3")

        // then (예외: 학생이 답변을 등록하는 경우)
        assertThrows(WrongRoleException::class.java) { answerService.addAnswer(question.id!!, requestOfStudent) }
    }
}