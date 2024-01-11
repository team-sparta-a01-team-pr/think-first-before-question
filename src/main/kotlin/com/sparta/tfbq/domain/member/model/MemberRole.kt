package com.sparta.tfbq.domain.member.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.sparta.tfbq.global.exception.ModelNotFoundException

enum class MemberRole {
    STUDENT,
    TUTOR;

    companion object {
        @JsonCreator
        fun of(role: String): MemberRole {
            return MemberRole.values().firstOrNull { role.uppercase() == it.name }
                ?: throw ModelNotFoundException("Role")
        }

        fun isTutor(role: MemberRole) = role == TUTOR

        fun isStudent(role: MemberRole) = role == STUDENT

    }
}
