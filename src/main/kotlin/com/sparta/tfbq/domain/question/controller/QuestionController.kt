package com.sparta.tfbq.domain.question.controller

import com.sparta.tfbq.domain.question.dto.request.AddQuestionRequest
import com.sparta.tfbq.domain.question.service.QuestionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/questions")
class QuestionController(private val service: QuestionService) {
    @PostMapping("/{memberId}")
    fun addQuestion(@RequestBody request: AddQuestionRequest, @PathVariable memberId: Long) {
        service.addQuestion(memberId, request)
    }
}