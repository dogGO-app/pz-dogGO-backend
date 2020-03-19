package pl.put.poznan.pz.doggo.modules.auth.user

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "_user")
class UserEntity(
        @Id
        @Type(type = "pg-uuid")
        val id: UUID = UUID.randomUUID(),

        @NotBlank
        @Email
        @Size(max = 50)
        val email: String,

        @NotBlank
        @Size(max = 120)
        val password: String,

        @ElementCollection(fetch = FetchType.EAGER)
        @Enumerated(EnumType.STRING)
        val roles: Set<Role>
)