package com.sparta.tfbq.domain.question.service

import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.domain.question.dto.request.AddQuestionRequest
import com.sparta.tfbq.domain.question.dto.response.QuestionResponse
import com.sparta.tfbq.domain.question.model.Question
import com.sparta.tfbq.domain.question.repository.QuestionRepository
import com.sparta.tfbq.global.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

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
        member.questions.add(question)
        questionRepository.save(question)

        return QuestionResponse.from(question)
    }
}