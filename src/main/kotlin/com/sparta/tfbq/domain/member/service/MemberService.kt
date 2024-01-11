package com.sparta.tfbq.domain.member.service

import com.sparta.tfbq.domain.member.dto.response.MemberResponse
import com.sparta.tfbq.domain.member.model.MemberRole
import com.sparta.tfbq.domain.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun checkEmail(email: String) {
        val isDuplicate = memberRepository.existsByEmail(email)
        if (isDuplicate) throw DuplicateValueException("email 중복됩니다.")
    }
    @Transactional
    fun checkNickname(nickname: String){
        val isDuplicate = memberRepository.existsByNickname(nickname)
        if (isDuplicate) throw DuplicatedValueException("nickname 중복됩니다.")
    }

    @Transactional
    fun findTutors() : List<MemberResponse> {
        val memberList = memberRepository.findAll().filter { it.role == MemberRole.TUTOR }
            .map { MemberResponse.from(it) }

        return memberList
    }
}