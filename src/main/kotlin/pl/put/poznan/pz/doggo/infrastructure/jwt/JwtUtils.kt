package pl.put.poznan.pz.doggo.infrastructure.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import pl.put.poznan.pz.doggo.modules.auth.security.userdetails.CustomUserDetails
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey
import javax.servlet.http.HttpServletRequest

@Component
object JwtUtils {
    private val logger: Logger = LoggerFactory.getLogger(JwtUtils::class.java)

    private const val jwtSecret =
            "g4OKWjQeWlcnERgrbJtCxSAmv7eJZrjjb/rBYBOTHKzCTO4FJnzneXQQ1J8XZGUJvoRMyNV+54PdqPztqFbJ4Q=="
    private const val jwtExpirationSecs = 86400L

    private const val TOKEN_HEADER = "Authorization"
    private const val TOKEN_PREFIX = "Bearer "

    fun generateToken(authentication: Authentication): String {
        val userDetails = authentication.principal as CustomUserDetails
        val expirationDate = run {
            val now = Instant.now()
            Date.from(now.plusSeconds(jwtExpirationSecs))
        }

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(userDetails.username)
                .setIssuedAt(Date())
                .setExpiration(expirationDate)
                .signWith(getSecret(), SignatureAlgorithm.HS512)
                .compact()
    }

    fun getUsernameFromToken(token: String): String = buildParser()
            .parseClaimsJws(token)
            .body
            .subject

    fun getTokenId(token: String): String = buildParser()
            .parseClaimsJws(token)
            .body
            .id

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

    fun parseJwt(request: HttpServletRequest): String? =
            request.getHeader(TOKEN_HEADER)?.let { authHeader ->
                if (authHeader.startsWith(TOKEN_PREFIX)) authHeader.removePrefix(TOKEN_PREFIX)
                else null
            }

    fun parseJwt(authHeader: String): String? =
            if (authHeader.startsWith(TOKEN_PREFIX)) authHeader.removePrefix(TOKEN_PREFIX)
            else null

    private fun buildParser(): JwtParser = Jwts.parserBuilder()
            .setSigningKey(jwtSecret)
            .build()

    private fun getSecret(): SecretKey {
        val keyBytes = Base64.getDecoder().decode(jwtSecret)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}