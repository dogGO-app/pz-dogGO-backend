package pl.put.poznan.pz.doggo.modules.auth.dto.requests

import javax.validation.constraints.NotBlank

data class LoginRequestDTO(
        @NotBlank
        val email: String,

        @NotBlank
        val password: String
)