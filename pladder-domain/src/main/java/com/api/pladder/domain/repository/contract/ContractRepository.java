package com.api.pladder.domain.repository.contract;

import com.api.pladder.domain.entity.contract.Contract;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.UUID;

public interface ContractRepository extends BaseRepository<Contract, UUID> {
}
