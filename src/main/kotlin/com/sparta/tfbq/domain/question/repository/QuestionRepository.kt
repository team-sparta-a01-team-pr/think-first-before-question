package com.sparta.tfbq.domain.question.repository

import com.sparta.tfbq.domain.question.model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Long> {
}