package pl.put.poznan.pz.doggo.modules.auth.security.jwt

import org.hibernate.annotations.Type
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class RevokedToken(
        @Id
        @Type(type = "pg-uuid")
        val id: UUID,

        val revocationDateTime: Instant = Instant.now()
)