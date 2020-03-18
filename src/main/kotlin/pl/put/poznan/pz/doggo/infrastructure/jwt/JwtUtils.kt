package pl.put.poznan.pz.doggo.infrastructure.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import pl.put.poznan.pz.doggo.modules.auth.security.userdetails.CustomUserDetails
import java.util.*
import javax.crypto.SecretKey

@Component
object JwtUtils {
    private val logger: Logger = LoggerFactory.getLogger(JwtUtils::class.java)

    @Value("\${doggo.security.token.secret}")
    private var jwtSecret: String = "g4OKWjQeWlcnERgrbJtCxSAmv7eJZrjjb/rBYBOTHKzCTO4FJnzneXQQ1J8XZGUJvoRMyNV+54PdqPztqFbJ4Q=="

    fun generateToken(authentication: Authentication): String {
        val userDetails = authentication.principal as CustomUserDetails

        return Jwts.builder()
                .setSubject(userDetails.username)
                .setIssuedAt(Date())
                .signWith(getSecret(), SignatureAlgorithm.HS512)
                .compact()
    }

    fun getUsernameFromToken(token: String): String = buildParser()
            .parseClaimsJws(token)
            .body
            .subject

    fun validateToken(token: String): Boolean {
        try {
            buildParser().parseClaimsJws(token)
            return true
        } catch (e: SecurityException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }

    private fun buildParser(): JwtParser = Jwts.parserBuilder()
            .setSigningKey(jwtSecret)
            .build()

    private fun getSecret(): SecretKey {
        val keyBytes = Base64.getDecoder().decode(jwtSecret)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}