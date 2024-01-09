package com.sparta.tfbq.global.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(WrongRoleException::class)
    fun handleWrongRoleException(e: Exception)
    = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
}