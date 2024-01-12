package com.sparta.tfbq.domain.answer.dto.request

import com.sparta.tfbq.domain.answer.model.Answer
import com.sparta.tfbq.domain.question.model.Question

data class AddAnswerRequest(
    val memberId: Long,
    val content: String
) {
    fun to(question: Question) = Answer(content, question)
}