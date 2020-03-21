package pl.put.poznan.pz.doggo.modules.auth.dto.requests

import javax.validation.constraints.NotBlank

data class LoginRequestDTO(
        @field:NotBlank
        val email: String,

        @field:NotBlank
        val password: String
)