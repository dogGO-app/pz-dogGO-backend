package pl.put.poznan.pz.doggo.modules.auth.user

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
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

        val role: String = "ROLE_USER"
)