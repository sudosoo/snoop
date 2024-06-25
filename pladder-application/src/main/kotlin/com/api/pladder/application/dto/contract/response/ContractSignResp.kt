package com.api.pladder.application.dto.contract.response

import com.api.pladder.application.dto.file.response.SignResp

class ContractSignResp(
    val contractId : String,
    val fileResp : List<SignResp>
)