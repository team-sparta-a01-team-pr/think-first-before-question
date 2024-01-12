package com.sparta.tfbq.domain.member.domain.tutor.service

import com.sparta.tfbq.domain.member.domain.tutor.dto.request.TutorCreateRequest
import com.sparta.tfbq.domain.member.domain.tutor.dto.response.TutorResponse
import com.sparta.tfbq.domain.member.common.service.MemberService
import com.sparta.tfbq.domain.member.model.MemberRole
import com.sparta.tfbq.domain.member.model.MemberRole.Companion.isTutor
import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.domain.question.dto.response.QuestionResponse
import com.sparta.tfbq.domain.question.repository.QuestionRepository
import com.sparta.tfbq.global.exception.WrongRoleException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TutorService(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository,
    private val questionRepository: QuestionRepository
) {
    @Transactional
    fun signUp(request: TutorCreateRequest): Long {
        val member = memberRepository.save(request.toEntity())
        return member.id!!
    }

    fun findTutors(): List<TutorResponse> =
        memberRepository.findAll()
            .filter { MemberRole.isTutor(it.role) }
            .map {
                TutorResponse.from(
                    member = it,
                    questions = questionRepository.findAllByQuestionTo(it.id!!)
                        .map { question -> QuestionResponse.from(question) })
            }

    fun findTutor(memberId: Long): TutorResponse {
        val member = memberService.getMemberById(memberId)
        if (!isTutor(member.role)) {
            throw WrongRoleException("튜터만 조회 가능합니다.")
        }
        val questions = questionRepository.findAllByQuestionTo(member.id!!)
        return TutorResponse.from(member, questions.map { QuestionResponse.from(it) })
    }
}
