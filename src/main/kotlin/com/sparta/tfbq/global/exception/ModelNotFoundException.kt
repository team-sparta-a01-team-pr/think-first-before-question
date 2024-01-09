package com.sparta.tfbq.global.exception

data class ModelNotFoundException(val id: Long, val modelName: String) :
    RuntimeException("Model '$modelName' not found with given id $id.")