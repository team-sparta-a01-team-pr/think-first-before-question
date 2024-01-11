package com.sparta.tfbq.domain.answer.controller

import com.sparta.tfbq.domain.answer.service.AnswerService
import org.springframework.stereotype.Controller

@Controller
class AnswerController(private val answerService: AnswerService) {
}