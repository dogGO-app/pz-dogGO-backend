package pl.put.poznan.pz.doggo.modules.auth.dto.responses

import java.util.*

data class JwtTokenInfoDTO(
        val token: String,
        val type: String = "Bearer",
        val tokenId: String,
        val expirationDate: Date,
        val userId: String,
        val email: String,
        val roles: List<String>
)