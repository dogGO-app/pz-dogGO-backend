package pl.put.poznan.pz.doggo.modules.auth.security.authorization

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.modules.auth.security.userdetails.CustomUserDetails
import pl.put.poznan.pz.doggo.modules.auth.user.UserEntity

@Service
class AuthorizationService {
    fun getCurrentUser(): UserEntity {
        val authentication = SecurityContextHolder.getContext().authentication
        return (authentication.principal as CustomUserDetails).userEntity
    }
}