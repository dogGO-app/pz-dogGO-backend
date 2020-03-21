package pl.put.poznan.pz.doggo.modules.dog

import org.hibernate.annotations.Type
import pl.put.poznan.pz.doggo.modules.auth.user.UserEntity
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "dog")
class Dog(
        @Id
        @Type(type = "pg-uuid")
        val id: UUID = UUID.randomUUID(),

        val name: String,

        val breed: String?,

        val color: String?,

        val description: String?,

        val lastVaccinationDate: LocalDate?,

        @ManyToOne
        val user: UserEntity
)