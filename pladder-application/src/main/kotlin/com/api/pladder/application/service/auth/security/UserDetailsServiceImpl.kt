package com.api.pladder.application.service.auth.security

import com.api.pladder.application.service.user.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class UserDetailsServiceImpl(
) : UserDetailsService{
    override fun loadUserByUsername(email: String): UserDetails {

        return UserDetailsImpl(email)
    }

}