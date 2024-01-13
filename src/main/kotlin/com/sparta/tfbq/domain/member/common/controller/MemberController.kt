package com.sparta.tfbq.domain.member.common.controller

import com.sparta.tfbq.domain.member.common.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/members")
class MemberController(
    private val memberService: MemberService
) {

    // R
    @GetMapping("/check")
    fun checkDuplicate(@RequestParam("value") value: String): ResponseEntity<Unit> {
        memberService.checkDuplicate(value)
        return ResponseEntity.ok().build()
    }

    // U

    // D
    @DeleteMapping("/{memberId}")
    fun withdrawal(@PathVariable memberId: Long): ResponseEntity<Unit> {
        memberService.withdrawal(memberId)
        return ResponseEntity.noContent().build()
    }

}