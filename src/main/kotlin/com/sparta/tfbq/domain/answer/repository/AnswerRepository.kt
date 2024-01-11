package com.sparta.tfbq.domain.answer.repository

import com.sparta.tfbq.domain.answer.model.Answer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository: JpaRepository<Answer, Long>