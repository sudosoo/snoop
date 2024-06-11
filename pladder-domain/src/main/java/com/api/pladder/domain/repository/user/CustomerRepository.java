package com.api.pladder.domain.repository.user;

import com.api.pladder.domain.entity.user.Customer;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends BaseRepository<Customer, UUID> {
    Optional<Customer> findByNickNameAndPasswd(String nickName,String passwd);
}
