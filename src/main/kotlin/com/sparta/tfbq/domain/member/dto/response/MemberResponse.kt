package com.sparta.tfbq.domain.member.dto.response

import com.sparta.tfbq.domain.member.model.Member

data class MemberResponse(
    val id: Long,
    val email: String,
    val name: String,
    val nickname: String,
    val role: String,
) {
    companion object {
        fun from(member: Member): MemberResponse {
            return MemberResponse(
                member.id!!,
                member.email,
                member.name,
                member.nickname,
                member.role.name
            )
        }
    }
}