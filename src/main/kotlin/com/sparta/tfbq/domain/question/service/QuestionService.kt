package com.sparta.tfbq.domain.question.service

import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.domain.question.dto.request.AddQuestionRequest
import com.sparta.tfbq.domain.question.dto.request.UpdateQuestionRequest
import com.sparta.tfbq.domain.question.dto.response.QuestionResponse
import com.sparta.tfbq.domain.question.repository.QuestionRepository
import com.sparta.tfbq.global.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun addQuestion(request: AddQuestionRequest): QuestionResponse {
        val member = memberRepository.findByIdOrNull(request.memberId)
            ?: throw ModelNotFoundException("Member")
        if (!memberRepository.existsById(request.tutorId)) throw ModelNotFoundException("Member")

        // 질문은 학생만 할 수 있다
        member.validateRole("STUDENT")

        val question = request.to(member)
        member.addQuestion(question)

        return QuestionResponse.from(questionRepository.save(question))
    }

    fun updateQuestion(questionId: Long, request: UpdateQuestionRequest): QuestionResponse {
        val member = memberRepository.findByIdOrNull(request.memberId)
            ?: throw ModelNotFoundException("Member")
        val question = questionRepository.findByIdOrNull(questionId)
            ?: throw ModelNotFoundException("Question")
        if (!memberRepository.existsById(request.tutorId)) throw ModelNotFoundException("Member")

        // 질문 수정은 학생만 할 수 있다
        member.validateRole("STUDENT")
        // 질문에 답변이 1개 이상 존재하는 경우, 학생은 질문을 수정할 수 없다
        question.availableToUpdate()

        member.removeQuestion(question)
        question.update(request.title, request.content, request.isPrivate)
        member.addQuestion(question)

        return QuestionResponse.from(questionRepository.save(question))
    }
}