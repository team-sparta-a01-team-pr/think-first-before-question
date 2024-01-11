package com.sparta.tfbq.domain.member.dto.response

import com.sparta.tfbq.domain.member.model.Member
import com.sparta.tfbq.domain.question.dto.response.QuestionResponse

open class MemberResponse(
    open val email: String,
    open val name: String,
) {
    companion object {
        fun from(member: Member) =
            StudentInfoResponse(
                email = member.email,
                name = member.name,
                nickname = member.nickname!!
            )

        fun from(member: Member, questions: List<QuestionResponse>) =
            TutorInfoResponse(
                email = member.email,
                name = member.name,
                questions = questions
            )
    }

}