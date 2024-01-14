package com.sparta.tfbq.auth.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.sparta.tfbq.auth.dto.request.MemberLoginRequest
import com.sparta.tfbq.auth.model.AUTHENTICATE_MEMBER
import com.sparta.tfbq.auth.model.AuthenticatedMember
import com.sparta.tfbq.auth.service.AuthService
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component

@Component
class VerifyMemberFilter(
    private val objectMapper: ObjectMapper,
    private val authService: AuthService
): Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpServletRequest = (request as HttpServletRequest)
        if (httpServletRequest.method == "POST") {
            val requestWrapper = AuthRequestWrapper(request)
            httpServletRequest
                .let { objectMapper.readValue(requestWrapper.reader, MemberLoginRequest::class.java) }
                .let { authService.verifyMember(it) }
                .let { AuthenticatedMember(it.email, it.role.name) }
                .let { requestWrapper.setAttribute(AUTHENTICATE_MEMBER, it) }

            chain?.doFilter(requestWrapper, response)
        }
    }
}