package com.api.pladder.application.service.common.jpa

import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.application.service.common.CommonService.checkNotNullData
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

interface JpaService<MODEL, ID> {
    var jpaRepository: BaseRepository<MODEL, ID>

    fun save(model: MODEL & Any) : MODEL {
        return jpaRepository.save(model)
    }

    fun saveAll(models: List<MODEL>) : List<MODEL> {
        return jpaRepository.saveAll(models)
    }

    fun findById(id : ID) : MODEL {
        checkNotNullData(id, "data is not found by id : $id")
        return jpaRepository.findById(id!!).orElseThrow { IllegalArgumentException("data is not found by id : $id") }
    }

    fun deleteById(id : ID) {
        checkNotNullData(id, "deleteById : id is null")
        return jpaRepository.deleteById(id!!)
    }

    fun findAllPagination(pageRequest: PageRequest): Page<MODEL> {
        checkNotNullData(pageRequest, "pageRequest is null")
        val pageModel = jpaRepository.findAll(pageRequest)
        if (pageModel.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Data Is Empty.")
        } else {
            return pageModel
        }
    }
}