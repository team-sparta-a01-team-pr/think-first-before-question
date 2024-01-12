package com.sparta.tfbq.domain.member.domain.tutor.controller

import com.sparta.tfbq.domain.member.domain.tutor.dto.request.TutorCreateRequest
import com.sparta.tfbq.domain.member.domain.tutor.dto.response.TutorResponse
import com.sparta.tfbq.domain.member.domain.tutor.service.TutorService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/tutors")
class TutorController(
    private val tutorService: TutorService
) {

    @PostMapping
    fun signUp(@RequestBody @Valid request: TutorCreateRequest): ResponseEntity<Unit> {
        val memberId = tutorService.signUp(request)
        return ResponseEntity.created(URI.create(String.format("/api/v1/members/%d", memberId))).build()
    }

    @GetMapping
    fun findTutors(): ResponseEntity<List<TutorResponse>> =
        ResponseEntity.ok(tutorService.findTutors())

    @GetMapping("/{memberId}")
    fun findMember(@PathVariable memberId: Long): ResponseEntity<TutorResponse> =
        ResponseEntity.ok(tutorService.findTutor(memberId))

}