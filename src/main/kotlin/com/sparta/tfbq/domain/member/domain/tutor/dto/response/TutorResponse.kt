package com.sparta.tfbq.domain.member.domain.tutor.dto.response

import com.sparta.tfbq.domain.member.Member
import com.sparta.tfbq.domain.question.dto.response.QuestionResponse

data class TutorResponse(
    val email: String,
    val name: String,
    val questions: List<QuestionResponse>? = emptyList()
) {

    companion object {
        fun from(member: Member) =
            TutorResponse(
                email = member.email,
                name = member.name,
            )

        fun from(member: Member, questions: List<QuestionResponse>) =
            TutorResponse(
                email = member.email,
                name = member.name,
                questions = questions
            )
    }
}
