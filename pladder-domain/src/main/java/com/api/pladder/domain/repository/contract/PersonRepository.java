package com.api.pladder.domain.repository.contract;

import com.api.pladder.domain.entity.contract.Person;
import com.api.pladder.domain.entity.contract.enums.PersonStatus;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends BaseRepository<Person, UUID> {
    Optional<Person> findByIdAndStatus(UUID id, PersonStatus status);
}
