package com.sparta.tfbq.domain.question.dto.request

data class UpdateQuestionRequest(
    val memberId: Long,
    val title: String,
    val content: String,
    val isPrivate: Boolean
)