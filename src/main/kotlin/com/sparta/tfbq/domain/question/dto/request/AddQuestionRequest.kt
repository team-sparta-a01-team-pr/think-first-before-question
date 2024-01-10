package com.sparta.tfbq.domain.question.dto.request

data class AddQuestionRequest (
    val memberId: Long,
    val tutorId: Long,
    val title: String,
    val content: String,
    val isPrivate: Boolean
)