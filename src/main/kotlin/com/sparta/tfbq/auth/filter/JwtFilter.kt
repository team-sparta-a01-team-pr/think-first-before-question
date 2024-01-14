package com.sparta.tfbq.auth.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.sparta.tfbq.auth.model.AUTHENTICATE_MEMBER
import com.sparta.tfbq.auth.model.AuthenticatedMember
import com.sparta.tfbq.auth.service.AuthService
import com.sparta.tfbq.global.auth.JwtUtil.createJwt
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.springframework.stereotype.Component

@Component
class JwtFilter(
    private val objectMapper: ObjectMapper,
    private val authService: AuthService,
) : Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        (request?.getAttribute(AUTHENTICATE_MEMBER) as AuthenticatedMember)
            .let { objectMapper.writeValueAsString(it) }
            .let { mutableMapOf<String, Any>(AUTHENTICATE_MEMBER to it) }
            .let { createJwt(it) }
            .let {
                authService.saveRefreshToken(it)
                objectMapper.writeValueAsString(it)
            }
            .let {
                response?.contentType = "application/json"
                response?.characterEncoding = "UTF-8"
                response?.writer?.write(it)
            }
        chain?.doFilter(request, response)
    }
}