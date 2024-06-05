package com.api.pladder.application.dto.contractContent.response

import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.entity.contract.enums.ContractStatus

class FindStatusContractResp (
    var waiting :Int= 0,
    var apply : Int = 0,
    var ongoing : Int= 0,
    var complete : Int= 0,
    var canceled: Int = 0,
){
    fun toResp(contracts: List<Contract>): FindStatusContractResp{
         contracts.forEach {
            when (it.status) {
                ContractStatus.WAITING -> waiting++
                ContractStatus.APPLY -> apply++
                ContractStatus.ONGOING -> ongoing++
                ContractStatus.COMPLETED -> complete++
                ContractStatus.CANCELED -> canceled++
            }
        }
        return this
    }


}