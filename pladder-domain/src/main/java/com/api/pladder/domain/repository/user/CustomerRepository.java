package com.api.pladder.domain.repository.user;

import com.api.pladder.domain.entity.user.Customer;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Customer,String> {
    Optional<Customer> findByEmail(String email);
}
