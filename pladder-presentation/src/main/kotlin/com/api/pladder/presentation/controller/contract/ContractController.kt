package com.api.pladder.presentation.controller.contract

import com.api.pladder.application.dto.common.BaseListRespV2
import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.service.contract.ContractService
import com.api.pladder.core.utils.provider.AuthDataProvider
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "계약서", description = "계약서 관련 API")
@RequestMapping("/api")
class ContractController (
    val service: ContractService
) : ResponseEntityCreation, AuthDataProvider {

    @ExplainSaveContract
    @PostMapping(value = ["/detective/contract"])
    fun register( request:RegisteredContract){
        service.register()
    }
    @ExplainSelectContract
    @GetMapping(value = ["/detective/contract","/customer/contract"])
    fun getContract( searchDto:
    ) : ResponseEntity<BaseResp>{

    }
    @ExplainSelectAllContract
    @GetMapping(value = ["/detective/quest,/customer/contract"])
    fun getAllContract( searchDto: QuestSearchDto,
                        @RequestParam(defaultValue = "0") page : Int,
                        @RequestParam(defaultValue = "10") size : Int,
    ) : ResponseEntity<BaseListRespV2>{

    }

    @ExplainDeleteContract
    @PutMapping(value = ["/detective/contract","/customer/contract"])
    fun deleteContract(){

    }

}