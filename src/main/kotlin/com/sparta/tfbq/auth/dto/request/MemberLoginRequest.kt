package com.sparta.tfbq.auth.dto.request

data class MemberLoginRequest(
    val email: String,
    val password: String
)