package com.api.pladder.application.service.contract.manager

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.application.dto.contract.mapper.ContractDtoMapper
import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.dto.contract.request.SuggestContractReq
import com.api.pladder.application.dto.contract.request.UpdateContractContentReq
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.contract.ContractRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ContractManager(
    val repository: ContractRepository
) : JpaService<Contract, UUID> {
    override var jpaRepository: BaseRepository<Contract, UUID> = repository

    fun register(request: RegisterContractReq, company: Company, customer: Customer) {
        val contract = ContractDtoMapper.toEntity(company, customer, request)
        save(contract)
    }

    fun suggest(contract: Contract, request: SuggestContractReq) {
        ContractDtoMapper.suggest(contract, request)
        save(contract)
    }


    fun updateContent(contract: Contract, request: UpdateContractContentReq) {

        ContractDtoMapper.updateContent(contract, request)
        save(contract)
    }


}
