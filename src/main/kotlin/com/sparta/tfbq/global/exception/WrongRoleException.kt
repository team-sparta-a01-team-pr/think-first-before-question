package com.sparta.tfbq.global.exception

data class WrongRoleException (val role: String)
    : RuntimeException("${role}s do not have permission to write.")