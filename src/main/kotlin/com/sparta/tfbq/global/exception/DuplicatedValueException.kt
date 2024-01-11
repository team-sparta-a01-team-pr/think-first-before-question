package com.sparta.tfbq.global.exception

class DuplicatedValueException(value: String) :
        RuntimeException("Duplicated $value. Please try it again.")