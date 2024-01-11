package com.sparta.tfbq.global.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    fun handleWrongRoleException(e: WrongRoleException) =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)

    fun handleModelNotFoundException(e: ModelNotFoundException) =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)

    fun handleAlreadyHaveAnswersException(e: AlreadyHaveAnswersException) =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)

    fun handleDuplicatedValueException(e: DuplicatedValueException) =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
}