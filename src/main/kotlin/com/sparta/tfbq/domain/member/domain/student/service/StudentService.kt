package com.sparta.tfbq.domain.member.domain.student.service

import com.sparta.tfbq.domain.member.common.service.MemberService
import com.sparta.tfbq.domain.member.domain.student.dto.request.StudentCreateRequest
import com.sparta.tfbq.domain.member.domain.student.dto.response.StudentResponse
import com.sparta.tfbq.domain.member.model.MemberRole
import com.sparta.tfbq.domain.member.model.MemberRole.Companion.isStudent
import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.global.exception.WrongRoleException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository
) {
    // C
    @Transactional
    fun signUp(request: StudentCreateRequest): Long {
        val member = memberRepository.save(request.toEntity())
        return member.id!!
    }

    // R
    fun findStudents(): List<StudentResponse> {
        return memberRepository.findAll().filter { MemberRole.isStudent(it.role) }
            .map { StudentResponse.from(it) }
    }

    fun findStudent(studentId: Long): StudentResponse {
        val student = memberService.getMemberById(studentId)
        if (!isStudent(student.role)) {
            throw WrongRoleException("학생만 조회 가능합니다.")
        }
        return StudentResponse.from(student)
    }

    // U


}

