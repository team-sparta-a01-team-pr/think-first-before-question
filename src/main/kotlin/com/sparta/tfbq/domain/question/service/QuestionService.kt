package com.sparta.tfbq.domain.question.service

import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.domain.question.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(val questionRepository: QuestionRepository, val memberRepository: MemberRepository) {
}