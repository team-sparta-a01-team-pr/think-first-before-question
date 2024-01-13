package com.sparta.tfbq.global.util


object RandomNicknameGenerator {
    private val wordList = ('0'..'9') + ('a'..'z') + ('A'..'Z')

    fun generateRandomNickname() = List(20) { wordList.random() }.joinToString(prefix = "익명의 회원", separator = "")
}
