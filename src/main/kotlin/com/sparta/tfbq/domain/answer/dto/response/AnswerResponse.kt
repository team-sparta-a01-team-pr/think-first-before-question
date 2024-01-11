package com.sparta.tfbq.domain.answer.dto.response

import java.time.LocalDateTime

data class AnswerResponse(
    val content: String,
    val createdAt: LocalDateTime
)