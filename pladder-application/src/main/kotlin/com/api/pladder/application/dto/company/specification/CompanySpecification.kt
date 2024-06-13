package com.api.pladder.application.dto.company.specification

import com.api.pladder.application.common.specification.BaseSpecification
import com.api.pladder.application.common.specification.SpecificationDto
import com.api.pladder.domain.entity.company.Company

object CompanySpecification : BaseSpecification<Company> {
    override val equalColumns: List<SpecificationDto>
        get() = listOf(

        )


}