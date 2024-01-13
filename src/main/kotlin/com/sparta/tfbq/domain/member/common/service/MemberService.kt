package com.sparta.tfbq.domain.member.common.service

import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.global.exception.DuplicatedValueException
import com.sparta.tfbq.global.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    // 회원 가입 중 입력값의 중복을 검증함
    fun checkDuplicate(value: String) {
        val isDuplicate = when (value.contains("@")) {
            true -> memberRepository.existsByEmail(value)
            false -> memberRepository.existsByNickname(value)
        }
        if (isDuplicate) throw DuplicatedValueException(value)
    }

    // 삭제 처리는 공통사항임
    fun withdrawal(memberId: Long) {
        val member = getMemberById(memberId)
        memberRepository.delete(member)
    }

    // 조회 처리는 공통사항임
    fun getMemberById(memberId: Long) =
        memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member")
}
