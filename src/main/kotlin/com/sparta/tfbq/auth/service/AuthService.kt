package com.sparta.tfbq.auth.service

import com.sparta.tfbq.auth.dto.request.MemberLoginRequest
import com.sparta.tfbq.auth.dto.request.TokenRefreshRequest
import com.sparta.tfbq.auth.dto.response.JwtResponse
import com.sparta.tfbq.domain.member.Member
import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.global.auth.JwtUtil.createJwt
import com.sparta.tfbq.global.auth.JwtUtil.getAuthenticateMember
import com.sparta.tfbq.global.auth.JwtUtil.getClaims
import com.sparta.tfbq.global.exception.ModelNotFoundException
import com.sparta.tfbq.global.util.PasswordEncoder.encode
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun saveRefreshToken(jwt: JwtResponse) {
        val authenticateMember = getAuthenticateMember(jwt.accessToken)
        val member = getMemberByEmail(authenticateMember.email)
        member.updateRefreshToken(jwt.refreshToken)
    }

    @Transactional
    fun refreshToken(request: TokenRefreshRequest): JwtResponse {
        val member = verifyMember(request.refreshToken)
        val claims = getClaims(request.refreshToken)
        val jwt = createJwt(claims)
        member.updateRefreshToken(jwt.refreshToken)

        return jwt
    }

    private fun verifyMember(refreshToken: String): Member {
        return memberRepository.findByRefreshToken(refreshToken) ?: throw RuntimeException()
    }

    fun verifyMember(request: MemberLoginRequest): Member {
        return memberRepository.findByEmailAndPassword(request.email, encode(request.password))
            ?: throw RuntimeException()
    }

    fun getMemberByEmail(email: String): Member {
        return memberRepository.findByEmail(email) ?: throw ModelNotFoundException("Member")
    }

}
