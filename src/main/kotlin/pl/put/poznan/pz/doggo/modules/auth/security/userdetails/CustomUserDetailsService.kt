package pl.put.poznan.pz.doggo.modules.auth.security.userdetails

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.modules.auth.user.UserEntityRepository

@Service
class CustomUserDetailsService(
        val userEntityRepository: UserEntityRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userEntityRepository.findByEmail(username)
                ?: throw UsernameNotFoundException("User with username: $username not found.")
        return CustomUserDetails(user)
    }
}