package com.sparta.tfbq.global.exception

class ModelNotFoundException(val modelName: String) :
    RuntimeException("Model '$modelName' not found with given id.")