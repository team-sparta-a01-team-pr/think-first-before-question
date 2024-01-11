package com.sparta.tfbq.domain.member.dto.response

import com.sparta.tfbq.domain.question.dto.response.QuestionResponse

data class TutorInfoResponse(
    override val email: String,
    override val name: String,
    val questions: List<QuestionResponse>
): MemberResponse(email, name)
