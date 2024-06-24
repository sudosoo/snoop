package com.api.pladder.application.dto.contract.response

import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.entity.contract.enums.ContractStatus

class CountContractStatusResp(
    contracts: List<Contract>
) {
    var waiting: Int = 0
    var apply: Int = 0
    var ongoing: Int = 0
    var complete: Int = 0
    var canceled: Int = 0

    init {
        val statusCount = contracts.groupingBy { it.status }.eachCount()
        waiting = statusCount[ContractStatus.WAITING] ?: 0
        apply = statusCount[ContractStatus.APPLY] ?: 0
        ongoing = statusCount[ContractStatus.ONGOING] ?: 0
        complete = statusCount[ContractStatus.COMPLETED] ?: 0
        canceled = statusCount[ContractStatus.CANCELED] ?: 0
    }
}