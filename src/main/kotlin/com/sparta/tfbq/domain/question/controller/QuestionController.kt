package com.sparta.tfbq.domain.question.controller

import com.sparta.tfbq.domain.question.service.QuestionService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/questions")
class QuestionController(val service: QuestionService) {
}