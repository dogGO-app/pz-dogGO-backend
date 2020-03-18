package pl.put.poznan.pz.doggo.modules.auth

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.infrastructure.exceptions.UserAlreadyExistsException
import pl.put.poznan.pz.doggo.infrastructure.jwt.JwtUtils
import pl.put.poznan.pz.doggo.modules.auth.dto.UserDTO
import pl.put.poznan.pz.doggo.modules.auth.dto.requests.LoginRequestDTO
import pl.put.poznan.pz.doggo.modules.auth.dto.requests.SignUpRequestDTO
import pl.put.poznan.pz.doggo.modules.auth.dto.responses.JwtResponseDTO
import pl.put.poznan.pz.doggo.modules.auth.security.userdetails.CustomUserDetails
import pl.put.poznan.pz.doggo.modules.auth.user.UserEntity
import pl.put.poznan.pz.doggo.modules.auth.user.UserEntityRepository

@Service
class AuthenticationService(
        private val authenticationManager: AuthenticationManager,
        private val passwordEncoder: PasswordEncoder,
        private val userEntityRepository: UserEntityRepository,
        private val jwtUtils: JwtUtils
) {

    fun authenticateUser(loginRequest: LoginRequestDTO): JwtResponseDTO {
        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password))
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils.generateToken(authentication)
        val userDetails = authentication.principal as CustomUserDetails
        val roles = userDetails.authorities.map { it.authority }

        return JwtResponseDTO(jwt,
                id = userDetails.getId().toString(),
                email = userDetails.username,
                roles = roles)
    }

    fun registerUser(signUpRequestDTO: SignUpRequestDTO): UserDTO {
        checkIfUserAlreadyExists(signUpRequestDTO.email)
        val user = UserEntity(email = signUpRequestDTO.email,
                password = passwordEncoder.encode(signUpRequestDTO.password))
        return UserDTO(userEntityRepository.save(user))
    }

    private fun checkIfUserAlreadyExists(email: String) {
        if (userEntityRepository.existsByEmail(email))
            throw UserAlreadyExistsException(email)
    }
}