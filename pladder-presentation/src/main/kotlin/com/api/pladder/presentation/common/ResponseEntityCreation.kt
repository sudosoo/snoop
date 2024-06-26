package com.api.pladder.presentation.common

import com.api.pladder.application.dto.common.BaseListRespV2
import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.core.enums.ErrorStatus
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity

interface ResponseEntityCreation {
    fun getListRespEntity(page: Page<*>): ResponseEntity<BaseListRespV2> {
        return ResponseEntity.ok(
            BaseListRespV2(
                content = page.content.toMutableList(),
                totalElements = page.totalElements,
                size = page.size,
                number = page.number
            )
        )
    }

    fun getRespEntity(resp: Any?, errorStatus: ErrorStatus = ErrorStatus.OK): ResponseEntity<BaseResp> {
        return ResponseEntity.ok(BaseResp(resp))
    }
}