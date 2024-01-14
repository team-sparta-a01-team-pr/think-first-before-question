package com.sparta.tfbq.auth.controller

import com.sparta.tfbq.auth.dto.request.MemberLoginRequest
import com.sparta.tfbq.auth.dto.request.TokenRefreshRequest
import com.sparta.tfbq.auth.dto.response.JwtResponse
import com.sparta.tfbq.auth.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: MemberLoginRequest): ResponseEntity<Unit> {
        return ResponseEntity.ok().build()
    }

    @PostMapping("/refreshtoken")
    fun refreshToken(@RequestBody request: TokenRefreshRequest): ResponseEntity<JwtResponse> {
        authService.refreshToken(request)
            .let { return ResponseEntity.ok(it) }
    }

}