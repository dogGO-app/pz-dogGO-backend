package pl.put.poznan.pz.doggo.modules.dog

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DogRepository : JpaRepository<Dog, UUID> {

    fun existsByNameAndUserId(name: String, userId: UUID): Boolean

    fun findByNameAndUserId(name: String, userId: UUID): Dog?

    fun findAllByUserId(userId: UUID): List<Dog>
}