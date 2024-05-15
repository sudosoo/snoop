package com.api.pladder.domain.repository.user;

import com.api.pladder.domain.entity.user.Admin;
import com.api.pladder.domain.entity.user.Customer;
import com.api.pladder.domain.repository.common.BaseRepository;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends BaseRepository<Admin,String> {
}
