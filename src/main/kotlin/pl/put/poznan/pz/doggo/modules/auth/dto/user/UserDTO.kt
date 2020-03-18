package pl.put.poznan.pz.doggo.modules.auth.dto.user

import pl.put.poznan.pz.doggo.modules.auth.user.UserEntity

data class UserDTO(

        val email: String,

        val role: String
) {
    constructor(user: UserEntity) : this(
            email = user.email,
            role = user.role
    )
}