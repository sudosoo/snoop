package com.api.pladder.application.common

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

object CommonService {
    fun <E> checkNotNullData(e: E?, message: String?): Boolean {
        if (e == null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, message)
        }
        return true
    }
}
