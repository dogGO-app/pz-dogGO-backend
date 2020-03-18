package pl.put.poznan.pz.doggo.modules.auth

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.modules.auth.user.UserEntityRepository

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder,
    private val userEntityRepository: UserEntityRepository
) {

}