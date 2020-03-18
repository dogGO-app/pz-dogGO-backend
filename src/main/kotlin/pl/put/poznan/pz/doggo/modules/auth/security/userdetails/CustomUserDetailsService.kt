package pl.put.poznan.pz.doggo.modules.auth.security.userdetails

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.infrastructure.exceptions.HeaderNotFoundException
import pl.put.poznan.pz.doggo.infrastructure.exceptions.UserNotFoundException
import pl.put.poznan.pz.doggo.infrastructure.jwt.JwtUtils.getUsernameFromToken
import pl.put.poznan.pz.doggo.infrastructure.jwt.JwtUtils.parseJwt
import pl.put.poznan.pz.doggo.modules.auth.user.UserEntity
import pl.put.poznan.pz.doggo.modules.auth.user.UserEntityRepository
import javax.servlet.http.HttpServletRequest

@Service
class CustomUserDetailsService(
        val userEntityRepository: UserEntityRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userEntityRepository.findByEmail(username)
                ?: throw UsernameNotFoundException("User with username: $username not found.")
        return CustomUserDetails(user)
    }

    fun getUser(request: HttpServletRequest): UserEntity {
        val email = getUsernameFromToken(parseJwt(request) ?: throw HeaderNotFoundException())
        return userEntityRepository.findByEmail(email)
                ?: throw UserNotFoundException(email)
    }
}