package com.sparta.tfbq.global.auth

import com.sparta.tfbq.auth.dto.response.JwtResponse
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.*

object JwtUtil {

    private const val SECRET = "5v87n5ytf9c9wn9yfco8w7adh8whonca8f87"

    private val key = Keys.hmacShaKeyFor(SECRET.toByteArray())

    fun createJwt(claims: Map<String, Any>): JwtResponse {
        val accessToken = createToken(claims, getExpireDateOfAccessToken())
        val refreshToken = createToken(emptyMap(), getExpireDateOfRefreshToken())

        return JwtResponse(accessToken, refreshToken)
    }

    private fun createToken(claims: Map<String, Any>, expireDate: Date): String {
        return Jwts.builder()
            .setSubject(claims["email"].toString())
            .addClaims(claims)
            .setExpiration(expireDate)
            .signWith(key)
            .compact()
    }

    fun getClaims(refreshToken: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(refreshToken)
            .body
    }

    private fun getExpireDateOfAccessToken(): Date {
        val expireTimeMils = 1000 * 60 * 60
        return Date(System.currentTimeMillis() + expireTimeMils)
    }

    private fun getExpireDateOfRefreshToken(): Date {
        val expireTimeMils = 1000L * 60 * 60 * 24
        return Date(System.currentTimeMillis() + expireTimeMils)
    }

}