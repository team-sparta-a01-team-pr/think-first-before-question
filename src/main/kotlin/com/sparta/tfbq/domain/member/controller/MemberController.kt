package com.sparta.tfbq.domain.member.controller

import com.sparta.tfbq.domain.member.dto.request.MemberCreateRequest
import com.sparta.tfbq.domain.member.dto.response.MemberResponse
import com.sparta.tfbq.domain.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/members")
class MemberController(
    private val memberService: MemberService
) {
    // C
    @PostMapping
    fun signUp(@RequestBody @Valid request: MemberCreateRequest): ResponseEntity<Unit> {
        val memberId = memberService.signUp(request)
        return ResponseEntity.created(URI.create(String.format("/api/v1/members/%d", memberId))).build()
    }

    // R
    @GetMapping("/check")
    fun checkDuplicate(@RequestParam("value") value: String): ResponseEntity<Unit> {
        memberService.checkDuplicate(value)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/tutors")
    fun findTutors(): ResponseEntity<List<MemberResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.findTutors())
    }

    @GetMapping("/{memberId}")
    fun findMember(@PathVariable memberId: Long): ResponseEntity<MemberResponse> {
        val response = memberService.findMember(memberId)
        return ResponseEntity.ok(response)
    }

    // U

    // D
    @DeleteMapping("/{memberId}")
    fun withdrawal(@PathVariable memberId: Long): ResponseEntity<Unit> {
        memberService.withdrawal(memberId)
        return ResponseEntity.noContent().build()
    }

}