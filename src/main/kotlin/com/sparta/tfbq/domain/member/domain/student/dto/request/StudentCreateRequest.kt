package com.sparta.tfbq.domain.member.domain.student.dto.request

import com.sparta.tfbq.domain.member.Member
import com.sparta.tfbq.domain.member.model.MemberRole
import com.sparta.tfbq.global.util.RandomNicknameGenerator.generateRandomNickname
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class StudentCreateRequest(
    @field:Email(message = "올바른 이메일 형식을 입력해주세요")
    @field:NotBlank(message = "이메일은 필수입력 사항입니다")
    val email: String,

    @field:NotBlank(message = "이름은 필수입력 사항입니다")
    val name: String,

    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}${'$'}",
        message = "비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다"
    )
    val password: String,

    val nickname: String?,
) {

    fun toEntity() =
        Member(
            email = email,
            name = name,
            password = password,
            nickname = nickname ?: generateRandomNickname(),
            memberRole = MemberRole.STUDENT
        )

}
