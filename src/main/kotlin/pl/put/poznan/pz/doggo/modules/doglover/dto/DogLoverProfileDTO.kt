package pl.put.poznan.pz.doggo.modules.doglover.dto

import pl.put.poznan.pz.doggo.modules.auth.user.UserEntity
import pl.put.poznan.pz.doggo.modules.doglover.DogLover
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

data class DogLoverProfileDTO(
        @field:NotBlank
        val firstName: String,

        @field:NotBlank
        val lastName: String,

        @field:Positive
        val age: Int,

        val hobby: String? = null
) {
    constructor(dogLover: DogLover) : this(
            dogLover.firstName,
            dogLover.lastName,
            dogLover.age,
            dogLover.hobby
    )

    fun toDogLoverEntity(user: UserEntity) = DogLover(
            user,
            firstName,
            lastName,
            age,
            hobby
    )
}