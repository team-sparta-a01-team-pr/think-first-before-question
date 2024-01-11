package com.sparta.tfbq.domain.answer.service

import com.sparta.tfbq.domain.answer.dto.request.AddAnswerRequest
import com.sparta.tfbq.domain.answer.repository.AnswerRepository
import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.domain.question.repository.QuestionRepository
import com.sparta.tfbq.global.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AnswerService(
    private val answerRepository: AnswerRepository,
    private val questionRepository: QuestionRepository,
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun addAnswer(questionId: Long, request: AddAnswerRequest): Long {
        val member = memberRepository.findByIdOrNull(request.memberId) ?: throw ModelNotFoundException("Member")
        val question = questionRepository.findByIdOrNull(questionId) ?: throw ModelNotFoundException("Question")

        // 답변은 튜터만 할 수 있다
        member.validateRole("TUTOR")

        val answer = request.to(question)
        question.addAnswer(answer)
        answerRepository.save(answer)

        return answer.id!!
    }
}