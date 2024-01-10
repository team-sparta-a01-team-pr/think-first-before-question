package com.sparta.tfbq.global.exception

class AlreadyHaveAnswersException :
    RuntimeException("This question cannot be updated because the answer already exists")