package com.api.pladder.domain.repository.contract;

import com.api.pladder.domain.entity.company.Company;
import com.api.pladder.domain.entity.contract.Contract;
import com.api.pladder.domain.entity.contract.enums.ContractStatus;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface ContractRepository extends BaseRepository<Contract, UUID> {

    List<Contract> findAllByCompanyAndStatus(Company company, ContractStatus status);
}
