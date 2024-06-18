package com.api.pladder.application.dto.user.detective.mapper

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdateInfoUserReq
import com.api.pladder.application.dto.user.detective.request.RegisterDetectiveCareerReq
import com.api.pladder.domain.entity.user.Detective
import java.time.LocalDate
import java.time.format.DateTimeFormatter


object DetectiveDtoMapper {

    fun toEntity(req : RegisterUserReq) : Detective {
        return Detective.of(req.email, req.passwd, req.phoneNumber)
    }
    fun updateInfo(detective: Detective, req: UpdateInfoUserReq) {
        return detective.updateInfo(req.phoneNumber)
    }

    fun updateCareer(detective: Detective,req: RegisterDetectiveCareerReq) {
        detective.career[req.period] = req.description

        val formatter = DateTimeFormatter.ofPattern("yyyy.MM")
        val period = req.period
        val dates = period.split(" ~ ")
        if (dates.size != 2) {
            throw IllegalArgumentException("잘못된 날짜 형식 입니다 :$period")
        }
        val startDate = LocalDate.parse(dates[0].trim(), formatter)
        val endDate = LocalDate.parse(dates[1].trim(), formatter)
        val total = endDate.year - startDate.year
        detective.updateTotalCareer(total)

    }

}