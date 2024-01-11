package com.sparta.tfbq.domain.answer.service

import com.sparta.tfbq.domain.answer.dto.request.AddAnswerRequest
import com.sparta.tfbq.domain.answer.repository.AnswerRepository
import com.sparta.tfbq.domain.member.model.MemberRole
import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.domain.question.repository.QuestionRepository
import com.sparta.tfbq.global.exception.ModelNotFoundException
import com.sparta.tfbq.global.exception.WrongRoleException
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
        val member = getMember(request.memberId)
        val question = getQuestion(questionId)

        // 답변은 튜터만 할 수 있다
        validateRole(member.role)

        val answer = request.to(question)
        question.addAnswer(answer)
        answerRepository.save(answer)

        return answer.id!!
    }

    private fun getMember(memberId: Long) =
        memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member")

    private fun getQuestion(questionId: Long) =
        questionRepository.findByIdOrNull(questionId) ?: throw ModelNotFoundException("Question")

    private fun validateRole(role: MemberRole) {
        if (MemberRole.isStudent(role)) throw WrongRoleException(role.name)
    }
}