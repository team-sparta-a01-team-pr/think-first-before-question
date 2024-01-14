package com.sparta.tfbq.auth.model

const val AUTHENTICATE_MEMBER = "authenticated"

class AuthenticatedMember(
    val email: String,
    val role: String
)