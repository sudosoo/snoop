package com.api.pladder.application.dto.contractContent.evidence

import com.api.pladder.domain.entity.evidence.Evidence

class EvidenceFileResp(evidence : Evidence, audioByte :List<ByteArray> ,imageByteArray: List<ByteArray>){
    val contractId = evidence.contractId
    val audio: List<ByteArray> = audioByte
    val image: List<ByteArray> = imageByteArray

}