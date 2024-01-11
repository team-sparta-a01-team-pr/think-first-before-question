package com.sparta.tfbq.domain.member.dto.response

import com.sparta.tfbq.domain.member.model.Member
import com.sparta.tfbq.domain.question.dto.response.QuestionResponse



data class TutorInfoResponse(
    val name: String,
    val email: String,
    val questions: List<QuestionResponse>
) {
    companion object {
        fun from(member: Member, questions: List<QuestionResponse>): TutorInfoResponse {
            return TutorInfoResponse(
                name = member.name,
                email = member.email,
                questions = questions
            )
        }
    }
}
