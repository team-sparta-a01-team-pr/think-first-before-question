package com.sparta.tfbq.domain.question.controller

import com.sparta.tfbq.domain.question.dto.request.AddQuestionRequest
import com.sparta.tfbq.domain.question.dto.request.UpdateQuestionRequest
import com.sparta.tfbq.domain.question.service.QuestionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/questions")
class QuestionController(private val service: QuestionService) {
    @PostMapping
    fun addQuestion(@RequestBody request: AddQuestionRequest): ResponseEntity<Unit> {
        val questionId = service.addQuestion(request)
        return ResponseEntity.created(URI.create(String.format("/api/v1/questions/%d", questionId))).build()
    }

    @PutMapping("/{questionId}")
    fun updateQuestion(@RequestBody request: UpdateQuestionRequest, @PathVariable questionId: Long): ResponseEntity<Unit> {
        service.updateQuestion(questionId, request)
        return ResponseEntity.ok().build()
    }
}