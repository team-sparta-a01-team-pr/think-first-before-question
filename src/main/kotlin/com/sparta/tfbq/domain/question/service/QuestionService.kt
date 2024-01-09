package com.sparta.tfbq.domain.question.service

import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.domain.question.dto.request.AddQuestionRequest
import com.sparta.tfbq.domain.question.model.Question
import com.sparta.tfbq.domain.question.repository.QuestionRepository
import com.sparta.tfbq.global.exception.ModelNotFoundException
import com.sparta.tfbq.global.exception.WrongRoleException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val memberRepository: MemberRepository
) {
    fun addQuestion(memberId: Long, request: AddQuestionRequest) {
        val member = memberRepository.findByIdOrNull(memberId)
            ?: throw ModelNotFoundException(memberId, "Member")

        // 튜터는 질문을 할 수 없다
        if (member.role.toString() == "TUTOR") throw WrongRoleException(member.role.toString())

        val question = Question(member, request.tutorId, request.title, request.content, request.isPrivate)
        member.questions.add(question)
        memberRepository.save(member)
    }
}