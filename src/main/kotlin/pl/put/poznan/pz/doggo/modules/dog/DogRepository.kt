package pl.put.poznan.pz.doggo.modules.dog

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DogRepository : JpaRepository<Dog, UUID> {

    fun existsByNameAndDogLoverId(name: String, dogLoverId: UUID): Boolean

    fun findByNameAndDogLoverId(name: String, dogLoverId: UUID): Dog?

    fun findAllByDogLoverId(dogLoverId: UUID): List<Dog>
}