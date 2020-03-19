package pl.put.poznan.pz.doggo.modules.auth.security.jwt

import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.infrastructure.exceptions.TokenRevokedException
import pl.put.poznan.pz.doggo.infrastructure.jwt.JwtUtils
import java.util.*

@Service
class JwtBlacklistService(
        private val revokedTokenRepository: RevokedTokenRepository,
        private val jwtUtils: JwtUtils
) {
    fun isTokenRevoked(token: String): Boolean =
            revokedTokenRepository.existsById(UUID.fromString(jwtUtils.getTokenId(token)))

    fun revokeToken(token: String) {
        if (isTokenRevoked(token)) throw TokenRevokedException()
        val tokenId = jwtUtils.getTokenId(token)
        revokedTokenRepository.save(RevokedToken(id = UUID.fromString(tokenId)))
    }
}