package com.sparta.tfbq.domain.member.domain.student.controller

import com.sparta.tfbq.domain.member.domain.student.dto.request.StudentCreateRequest
import com.sparta.tfbq.domain.member.domain.student.dto.response.StudentResponse
import com.sparta.tfbq.domain.member.domain.student.service.StudentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/students")
class StudentController(
    private val studentService: StudentService
) {
    // C
    @PostMapping
    fun signUp(@RequestBody @Valid request: StudentCreateRequest): ResponseEntity<Unit> {
        val memberId = studentService.signUp(request)
        return ResponseEntity.created(URI.create(String.format("/api/v1/members/%d", memberId))).build()
    }

    // R
    @GetMapping
    fun findStudents(): ResponseEntity<List<StudentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(studentService.findStudents())
    }

    @GetMapping("/{studentId}")
    fun findStudent(@PathVariable studentId: Long): ResponseEntity<StudentResponse> {
        val response = studentService.findStudent(studentId)
        return ResponseEntity.ok(response)
    }
}