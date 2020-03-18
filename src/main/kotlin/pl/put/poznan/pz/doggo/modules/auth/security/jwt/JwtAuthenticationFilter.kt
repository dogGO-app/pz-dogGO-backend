package pl.put.poznan.pz.doggo.modules.auth.security.jwt

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import pl.put.poznan.pz.doggo.infrastructure.jwt.JwtUtils
import pl.put.poznan.pz.doggo.modules.auth.security.userdetails.CustomUserDetailsService
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
        private val userDetailsService: CustomUserDetailsService
) : OncePerRequestFilter() {
    private companion object {
        const val TOKEN_HEADER = "Authorization"
    }

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
    ) {
        try {
            val jwt = parseJwt(request)
            if (jwt != null && JwtUtils.validateToken(jwt)) {
                val username = JwtUtils.getUsernameFromToken(jwt)
                val userDetails = userDetailsService.loadUserByUsername(username)

                val authenticationToken = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities
                )
                authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = authenticationToken
            }
        } catch (e: Exception) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response)
    }

    private fun parseJwt(request: HttpServletRequest): String? =
            request.getHeader(TOKEN_HEADER)
}