package com.sparta.tfbq.domain.question.repository

import com.sparta.tfbq.domain.question.model.Question
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<Question, Long> {
}