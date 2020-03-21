package pl.put.poznan.pz.doggo.modules.doglover

import pl.put.poznan.pz.doggo.modules.auth.user.UserEntity
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

@Entity
class DogLover(
        @Id
        @Column(name = "user_id")
        val id: UUID,

        @OneToOne
        @MapsId(value = "user_id")
        val user: UserEntity,

        @field:NotBlank
        val firstName: String,

        @field:NotBlank
        val lastName: String,

        @field:Positive
        val age: Int,

        val hobby: String? = null
) {
    constructor(user: UserEntity, firstName: String, lastName: String, age: Int, hobby: String? = null) : this(
            id = user.id,
            user = user,
            firstName = firstName,
            lastName = lastName,
            age = age,
            hobby = hobby
    )
}
