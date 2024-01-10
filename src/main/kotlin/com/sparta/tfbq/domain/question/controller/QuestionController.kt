package com.sparta.tfbq.domain.question.controller

import com.sparta.tfbq.domain.question.dto.request.AddQuestionRequest
import com.sparta.tfbq.domain.question.dto.request.UpdateQuestionRequest
import com.sparta.tfbq.domain.question.service.QuestionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/questions")
class QuestionController(private val service: QuestionService) {
    @PostMapping
    fun addQuestion(@RequestBody request: AddQuestionRequest) =
        ResponseEntity.status(HttpStatus.CREATED).body(service.addQuestion(request))

    @PutMapping("/{questionId}")
    fun updateQuestion(@RequestBody request: UpdateQuestionRequest, @PathVariable questionId: Long) {
        ResponseEntity.status(HttpStatus.OK).body(service.updateQuestion(questionId, request))
    }
}