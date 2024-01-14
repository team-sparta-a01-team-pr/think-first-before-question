package com.sparta.tfbq.auth.filter

import io.micrometer.common.util.StringUtils
import jakarta.servlet.ReadListener
import jakarta.servlet.ServletInputStream
import jakarta.servlet.ServletRequest
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class AuthRequestWrapper(request: HttpServletRequest) : HttpServletRequestWrapper(request) {
    private val encoding: Charset
    private val rawData: ByteArray

    init {
        var characterEncoding = request.characterEncoding
        if (StringUtils.isBlank(characterEncoding)) {
            characterEncoding = StandardCharsets.UTF_8.name()
        }

        this.encoding = Charset.forName(characterEncoding)

        val inputStream = request.inputStream
        val buffer = ByteArray(1024)
        val byteArrayOutputStream = ByteArrayOutputStream()
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead)
        }

        this.rawData = byteArrayOutputStream.toByteArray()
    }

    override fun getInputStream(): ServletInputStream {
        val byteArrayInputStream = ByteArrayInputStream(this.rawData)
        return object : ServletInputStream() {

            override fun read() = byteArrayInputStream.read()

            override fun isFinished() = false

            override fun isReady() = false

            override fun setReadListener(listener: ReadListener?) {

            }

        }
    }

    override fun getReader() = BufferedReader(InputStreamReader(this.inputStream, this.encoding))
    override fun getRequest(): ServletRequest = super.getRequest()

}