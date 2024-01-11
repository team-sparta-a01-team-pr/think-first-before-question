package com.sparta.tfbq.domain.member.service

import com.sparta.tfbq.domain.member.dto.response.MemberResponse
import com.sparta.tfbq.domain.member.dto.response.TutorInfoResponse
import com.sparta.tfbq.domain.member.model.Member
import com.sparta.tfbq.domain.member.model.MemberRole
import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.global.exception.DuplicatedValueException
import com.sparta.tfbq.domain.question.dto.response.QuestionResponse
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun checkEmail(email: String) {
        val isDuplicate = memberRepository.existsByEmail(email)
        if (isDuplicate) throw DuplicatedValueException("email")
    }
    @Transactional
    fun checkNickname(nickname: String){
        val isDuplicate = memberRepository.existsByNickname(nickname)
        if (isDuplicate) throw DuplicatedValueException("nickname")
    }

    @Transactional
    fun findTutors() : List<MemberResponse> {
        val memberList = memberRepository.findAll().filter { it.role == MemberRole.TUTOR }
            .map { MemberResponse.from(it) }

        return memberList
    }

    fun findTutorInfo(tutorId: Long): TutorInfoResponse {
        val member: Member = memberRepository.findByIdOrNull(tutorId) ?: throw Exception()
        val questions = member.questions.map { QuestionResponse.from(it) }

        return TutorInfoResponse.from(member, questions)
    }
}