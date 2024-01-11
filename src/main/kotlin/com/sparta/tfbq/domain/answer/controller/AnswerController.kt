package com.sparta.tfbq.domain.answer.controller

import com.sparta.tfbq.domain.answer.dto.request.AddAnswerRequest
import com.sparta.tfbq.domain.answer.service.AnswerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/questions/{questionId}")
class AnswerController(private val answerService: AnswerService) {
    @PostMapping
    fun addAnswer(@PathVariable questionId: Long, @RequestBody request: AddAnswerRequest): ResponseEntity<Unit> {
        val answerId = answerService.addAnswer(questionId, request)
        return ResponseEntity.created(URI.create(String.format("/api/v1/answers/%d", answerId))).build()
    }
}