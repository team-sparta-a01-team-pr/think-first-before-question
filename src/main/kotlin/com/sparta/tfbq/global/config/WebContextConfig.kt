package com.sparta.tfbq.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.sparta.tfbq.auth.filter.JwtFilter
import com.sparta.tfbq.auth.filter.VerifyMemberFilter
import com.sparta.tfbq.auth.service.AuthService
import jakarta.servlet.Filter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebContextConfig {

    @Bean
    fun verifyMemberFilter(
        objectMapper: ObjectMapper,
        authService: AuthService
    ): FilterRegistrationBean<Filter> {
        val filterRegistrationBean = FilterRegistrationBean<Filter>()
        filterRegistrationBean.filter = VerifyMemberFilter(objectMapper, authService)
        filterRegistrationBean.order = 1
        filterRegistrationBean.urlPatterns = listOf("/api/v1/auth/login")
        return filterRegistrationBean
    }

    @Bean
    fun jwtFilter(
        objectMapper: ObjectMapper,
        authService: AuthService
    ): FilterRegistrationBean<Filter> {
        val filterRegistrationBean = FilterRegistrationBean<Filter>()
        filterRegistrationBean.filter = JwtFilter(objectMapper, authService)
        filterRegistrationBean.order = 2
        filterRegistrationBean.urlPatterns = listOf("/api/v1/auth/login")
        return filterRegistrationBean
    }
}