package com.sparta.tfbq.domain.member.repository

import com.sparta.tfbq.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long> {
    fun existsByEmail(email: String): Boolean
    fun existsByNickname(nickname: String): Boolean
    fun existsEmailAndPassword(name: String, password: String): Boolean
}