package com.sparta.tfbq.global.util


class RandomNicknameGenerator {
    companion object {
        private val wordList = ('0'..'9') + ('a'..'z') + ('A'..'Z')

        fun generateRandomNickname() = List(20) { wordList.random() }.joinToString(prefix = "익명의 회원", separator = "")
    }
}
