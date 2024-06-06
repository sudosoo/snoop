package com.api.pladder.application.dto.progressHistory.response

import java.time.LocalDateTime

class ProgressHistoryResp(
    val recordingTime: LocalDateTime,
    val content : String,
)