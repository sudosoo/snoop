package com.api.pladder.application.dto.contract.response

import com.api.pladder.application.dto.file.response.FileResp

class ContractSignResp(
    val contractId : String,
    val fileResp : List<FileResp>
)