package pl.put.poznan.pz.doggo.modules.auth.dto.responses

data class JwtResponseDTO(
        val token: String,
        val type: String = "Bearer",
        val id: String,
        val email: String,
        val roles: List<String>
)