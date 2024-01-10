package com.sparta.tfbq.domain.question.dto.response

import com.sparta.tfbq.domain.answer.model.Answer
import com.sparta.tfbq.domain.question.model.Question
import java.time.LocalDateTime

data class QuestionResponse(
    val id: Long?,
    val questionTo: Long,
    val title: String,
    val content: String,
    val isPrivate: Boolean,
    val answers: MutableList<Answer>, // TODO : 추후 리스트 자료형을 AnswerResponse로 수정 필요
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {

    companion object {
        fun from(question: Question) =
            QuestionResponse(
                question.id,
                question.questionTo,
                question.title,
                question.content,
                question.isPrivate,
                question.answers,
                question.createdAt,
                question.updatedAt
            )
    }
}