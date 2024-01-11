package com.sparta.tfbq.domain.answer.service

import com.sparta.tfbq.domain.answer.repository.AnswerRepository
import com.sparta.tfbq.domain.question.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val answerRepository: AnswerRepository,
    private val questionRepository: QuestionRepository
) {
}