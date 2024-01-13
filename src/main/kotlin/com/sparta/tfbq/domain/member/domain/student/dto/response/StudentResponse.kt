package com.sparta.tfbq.domain.member.domain.student.dto.response

import com.sparta.tfbq.domain.member.Member

data class StudentResponse(
    val email: String,
    val name: String,
    val nickname: String
) {
    companion object {
        fun from(member: Member) =
            StudentResponse(
                email = member.email,
                name = member.name,
                nickname = member.nickname!!
            )
    }
}