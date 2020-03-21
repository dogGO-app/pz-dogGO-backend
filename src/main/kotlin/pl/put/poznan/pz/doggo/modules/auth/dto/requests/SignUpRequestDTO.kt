package pl.put.poznan.pz.doggo.modules.auth.dto.requests

import pl.put.poznan.pz.doggo.infrastructure.exceptions.RoleNotFoundException
import pl.put.poznan.pz.doggo.modules.auth.user.Role
import javax.validation.constraints.Email
import javax.validation.constraints.Size

data class SignUpRequestDTO(
        @field:Email
        @field:Size(max = 50)
        val email: String,

        @field:Size(min = 8, max = 120)
        val password: String,

        val roles: Set<String> = DEFAULT_ROLES
) {
    private companion object {
        val DEFAULT_ROLES = setOf("ROLE_USER")
    }

    fun getEnumRoles() = try {
        roles.map { Role.valueOf(it) }.toSet()
    } catch (e: IllegalArgumentException) {
        throw RoleNotFoundException()
    }
}