package com.sparta.tfbq.domain.question.dto.response

//import com.sparta.tfbq.domain.answer.dto.response.AnswerResponse
import com.sparta.tfbq.domain.question.model.Question
import java.time.LocalDateTime

data class QuestionResponse(
    val title: String,
    val content: String,
    val isPrivate: Boolean,
    val createdAt: LocalDateTime,
//    val answers: List<AnswerResponse>
) {

    companion object {
        fun from(question: Question) =
            QuestionResponse(
                question.title,
                question.content,
                question.isPrivate,
                question.createdAt,
//                question.answers.map { AnswerResponse.from(it) }
            )
    }
}