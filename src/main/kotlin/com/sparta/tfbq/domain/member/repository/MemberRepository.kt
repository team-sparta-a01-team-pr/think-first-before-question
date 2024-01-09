package com.sparta.tfbq.domain.member.repository

import com.sparta.tfbq.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
}