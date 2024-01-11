package com.sparta.tfbq.domain.question.service

import com.sparta.tfbq.domain.answer.model.Answer
import com.sparta.tfbq.domain.answer.repository.AnswerRepository
import com.sparta.tfbq.domain.member.model.Member
import com.sparta.tfbq.domain.member.model.MemberRole
import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.domain.question.dto.request.AddQuestionRequest
import com.sparta.tfbq.domain.question.dto.request.UpdateQuestionRequest
import com.sparta.tfbq.domain.question.repository.QuestionRepository
import com.sparta.tfbq.global.exception.AlreadyHaveAnswersException
import com.sparta.tfbq.global.exception.ModelNotFoundException
import com.sparta.tfbq.global.exception.WrongRoleException
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
@Transactional
class QuestionServiceTest {
    @Autowired
    lateinit var service: QuestionService

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var questionRepository: QuestionRepository

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

        // when (정상 처리)
        val requestOfStudent = AddQuestionRequest(student.id!!, tutor.id!!, "질문 제목 예시", "질문 내용 예시", false)
        service.addQuestion(requestOfStudent)

        // then (정상 처리)
        assertThat(student.questions.size).isEqualTo(1)
        assertThat(student.questions[0].title).isEqualTo("질문 제목 예시")

        // when (예외)
        val requestOfTutor = AddQuestionRequest(tutor.id!!, tutor.id!!, "질문 제목 예시", "질문 내용 예시", false)

        // then (예외)
        assertThrows(WrongRoleException::class.java) { service.addQuestion(requestOfTutor) }
    }

    @Test
    fun updateQuestion() {
        // given
        val student = memberRepository.findByIdOrNull(1L) ?: throw ModelNotFoundException("")
        val tutor = memberRepository.findByIdOrNull(2L) ?: throw ModelNotFoundException("")

        val requestOfStudent = AddQuestionRequest(student.id!!, tutor.id!!, "질문 제목 예시", "질문 내용 예시", false)
        service.addQuestion(requestOfStudent)

        val question = questionRepository.findByIdOrNull(1L) ?: throw ModelNotFoundException("")

        // when (정상 처리)
        val updateRequest = UpdateQuestionRequest(student.id!!, tutor.id!!, "수정된 제목 예시", "수정된 내용 예시", true)
        service.updateQuestion(question.id!!, updateRequest)

        // then (정상 처리)
        assertThat(student.questions[0].title).isEqualTo("수정된 제목 예시")
        assertThat(student.questions[0].content).isEqualTo("수정된 내용 예시")
        assertThat(student.questions[0].isPrivate).isEqualTo(true)

        // when (예외)
        question.answers.add(Answer("답변 예시", question))

        // then (예외)
        assertThrows(AlreadyHaveAnswersException::class.java) { service.updateQuestion(question.id!!, updateRequest) }
    }

    @Test
    fun deleteQuestion() {
        // given
        val student = memberRepository.findByIdOrNull(1L) ?: throw ModelNotFoundException("")
        val tutor = memberRepository.findByIdOrNull(2L) ?: throw ModelNotFoundException("")

        val requestOfStudent = AddQuestionRequest(student.id!!, tutor.id!!, "질문 제목 예시", "질문 내용 예시", false)
        service.addQuestion(requestOfStudent)

        val question = questionRepository.findByIdOrNull(1L) ?: throw ModelNotFoundException("")

        // when
        question.answers.add(Answer("답변 예시", question))
        question.answers.add(Answer("답변 예시2", question))
        service.deleteQuestion(student.id!!, question.id!!)

        // then
        assertThat(student.questions.size).isEqualTo(0)
        assertThrows(ModelNotFoundException::class.java) {
            questionRepository.findByIdOrNull(1L) ?: throw ModelNotFoundException("") }
    }
}