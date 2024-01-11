package com.sparta.tfbq.domain.answer.dto.response

import com.sparta.tfbq.domain.answer.model.Answer
import java.time.LocalDateTime

data class AnswerResponse(
    val content: String,
    val createdAt: LocalDateTime
) {
  
    companion object {
        fun from(answer: Answer): AnswerResponse {
            return AnswerResponse(answer.content, answer.createdAt)
        }
    }
    
}
