package com.sparta.tfbq.domain.member.service

import com.sparta.tfbq.domain.member.dto.request.MemberCreateRequest
import com.sparta.tfbq.domain.member.dto.response.MemberResponse
import com.sparta.tfbq.domain.member.exception.UnauthorizedValueException
import com.sparta.tfbq.domain.member.model.MemberRole.Companion.isTutor
import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.domain.question.dto.response.QuestionResponse
import com.sparta.tfbq.global.exception.DuplicatedValueException
import com.sparta.tfbq.global.exception.ModelNotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun signUp(request: MemberCreateRequest): Long {
        if (isTutor(request.role) && request.nickname?.length!! > 0) {
            throw UnauthorizedValueException("튜터는 닉네임을 사용할 수 없습니다")
        }
        val member = memberRepository.save(request.toEntity())
        return member.id!!
    }

    @Transactional
    fun checkDuplicate(value: String) {
        val isDuplicate = when (value.contains("@")) {
            true -> memberRepository.existsByEmail(value)
            false -> memberRepository.existsByNickname(value)
        }
        if (isDuplicate) throw DuplicatedValueException(value)
    }

    @Transactional
    fun findTutors(): List<MemberResponse> {
        return memberRepository.findAll().filter { isTutor(it.role) }
            .map { MemberResponse.from(it) }
    }

    fun findMember(memberId: Long): MemberResponse {
        val member = getMemberById(memberId)

        return when (isTutor(member.role)) {
            true -> {
                val questions = member.questions.map { QuestionResponse.from(it) }
                MemberResponse.from(member, questions)
            }

            false -> MemberResponse.from(member)
        }
    }

    fun withdrawal(memberId: Long) {
        val member = getMemberById(memberId)
        memberRepository.delete(member)
    }

    fun getMemberById(memberId: Long) =
        memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member")
}
