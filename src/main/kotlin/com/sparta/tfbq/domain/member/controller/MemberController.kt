package com.sparta.tfbq.domain.member.controller

import com.sparta.tfbq.domain.member.dto.response.MemberResponse
import com.sparta.tfbq.domain.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/members")
class MemberController (
    private val memberService: MemberService
) {

    @GetMapping("/check")
     fun checkEmail(@RequestParam("email") email: String): ResponseEntity<Unit> {
        memberService.checkEmail(email)
        return ResponseEntity.ok().build()
     }

    @GetMapping("/check")
    fun checkNickname (@RequestParam("nickname") nickname: String): ResponseEntity<Unit>{
        memberService.checkNickname(nickname)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/tutors")
    fun findTutors (): ResponseEntity<List<MemberResponse>>{
          return ResponseEntity
              .status(HttpStatus.OK)
              .body(memberService.findTutors())
    }

}