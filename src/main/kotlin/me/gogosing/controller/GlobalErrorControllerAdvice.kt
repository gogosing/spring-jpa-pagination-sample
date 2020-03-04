package me.gogosing.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * Created by JinBum Jeong on 2020/03/04.
 */
@RestControllerAdvice
class GlobalErrorControllerAdvice : ResponseEntityExceptionHandler() {

    override fun handleExceptionInternal(
        exception: java.lang.Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        logger.error("handleExceptionInternal", exception)
        return super.handleExceptionInternal(exception, body, headers, status, request)
    }
}
