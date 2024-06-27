package com.api.pladder.application.service.common

import org.junit.jupiter.api.Test
import java.util.*

class UUIDGeneratorTest {

    @Test
    fun `UUID 생성`() {
        val count = 10
        repeat(count) { index ->
            println("${index + 1}: ${UUID.randomUUID()}")
        }
    }

}
