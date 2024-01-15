package com.sparta.tfbq.auth.model

import com.fasterxml.jackson.annotation.JsonProperty

const val AUTHENTICATE_MEMBER = "authenticated"

data class AuthenticatedMember(
    @JsonProperty("email") val email: String,
    @JsonProperty("role") val role: String
)