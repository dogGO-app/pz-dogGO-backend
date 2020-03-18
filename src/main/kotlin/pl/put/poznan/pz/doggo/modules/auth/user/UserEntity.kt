package pl.put.poznan.pz.doggo.modules.auth.user

import com.fasterxml.jackson.annotation.JsonView
import org.hibernate.annotations.Type
import pl.put.poznan.pz.doggo.views.Views
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
        @JsonView(Views.Id::class)
        val id: UUID = UUID.randomUUID(),

        @NotBlank
        @Email
        @Size(max = 50)
        @JsonView(Views.Public::class)
        val email: String,

        @NotBlank
        @Size(max = 120)
        @JsonView(Views.Internal::class)
        val password: String,

        @JsonView(Views.Public::class)
        val role: String = "ROLE_USER"
)