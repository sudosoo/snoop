package com.api.pladder.core.utils.date

import java.time.LocalDate
import java.time.LocalDateTime

object DateUtil {
    val DEFAULT_DATE: LocalDate = LocalDate.of(9999, 12, 31)
    val DEFAULT_DATE_TIME: LocalDateTime = LocalDateTime.of(9999, 12, 31, 23, 59, 59)
}
