package com.sparta.tfbq.domain.member.dto.response

data class StudentInfoResponse(
    override val email: String,
    override val name: String,
    val nickname: String
) : MemberResponse(email, name)