package com.sparta.tfbq.domain.question.dto.request

data class AddQuestionRequest (
    val tutorId: Long,
    var title: String,
    var content: String,
    var isPrivate: Boolean
)