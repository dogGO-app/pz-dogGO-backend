package pl.put.poznan.pz.doggo.modules.auth.dto.user

import pl.put.poznan.pz.doggo.modules.auth.user.Role
import pl.put.poznan.pz.doggo.modules.auth.user.UserEntity

data class UserDTO(

        val email: String,

        val roles: Set<Role>
) {
    constructor(user: UserEntity) : this(
            email = user.email,
            roles = user.roles
    )
}