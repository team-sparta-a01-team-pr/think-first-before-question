package com.sparta.tfbq.domain.question.dto.request

import com.sparta.tfbq.domain.member.model.Member
import com.sparta.tfbq.domain.question.model.Question

data class AddQuestionRequest(
    val memberId: Long,
    val tutorId: Long,
    val title: String,
    val content: String,
    val isPrivate: Boolean
) {
    fun to(member: Member) =
        Question(member, tutorId, title, content, isPrivate)
}