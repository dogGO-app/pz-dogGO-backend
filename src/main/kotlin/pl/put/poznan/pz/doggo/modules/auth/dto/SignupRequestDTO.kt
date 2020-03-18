package pl.put.poznan.pz.doggo.modules.auth.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class SignUpRequestDTO(
        @NotBlank
        @Email
        @Size(max = 50)
        val email: String,

        @NotBlank
        @Size(max = 120)
        val password: String
)