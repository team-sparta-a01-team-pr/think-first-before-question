package com.sparta.tfbq.domain.answer.controller

import com.sparta.tfbq.domain.answer.service.AnswerService
import org.springframework.web.bind.annotation.RestController

@RestController
class AnswerController(private val answerService: AnswerService) {
}