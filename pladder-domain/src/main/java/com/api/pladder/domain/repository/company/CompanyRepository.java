package com.api.pladder.domain.repository.company;

import com.api.pladder.domain.entity.user.Customer;
import com.api.pladder.domain.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends BaseRepository<Customer,String> {
}
