package com.sparta.tfbq.auth.dto.response

data class JwtResponse(
    val accessToken: String,
    val refreshToken: String
)