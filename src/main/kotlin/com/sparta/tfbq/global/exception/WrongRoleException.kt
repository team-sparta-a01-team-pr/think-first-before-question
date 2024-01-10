package com.sparta.tfbq.global.exception

class WrongRoleException (val role: String)
    : RuntimeException("${role}s do not have permission to write.")