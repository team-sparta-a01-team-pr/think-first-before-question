package com.sparta.tfbq.global.util

import java.util.Base64

object PasswordEncoder {

    fun encode(password: String) = Base64.getEncoder().encodeToString(password.toByteArray())!!

    fun decode(encodedPassword: String) = Base64.getEncoder()
        .encodeToString(encodedPassword.toByteArray())!!

}