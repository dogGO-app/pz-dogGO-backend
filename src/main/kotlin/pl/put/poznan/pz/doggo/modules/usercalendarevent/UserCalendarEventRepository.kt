package pl.put.poznan.pz.doggo.modules.usercalendarevent

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.put.poznan.pz.doggo.modules.dog.Dog
import pl.put.poznan.pz.doggo.modules.doglover.DogLover
import java.time.Instant
import java.util.*

@Repository
interface UserCalendarEventRepository : JpaRepository<UserCalendarEvent, UUID> {

    fun findAllByDogLoverAndDateTimeAfter(dogLover: DogLover, dateTime: Instant): List<UserCalendarEvent>

    fun findByIdAndDogLover(id: UUID, dogLover: DogLover): UserCalendarEvent?

    fun existsByDateTimeAndDogLoverAndDog(dateTime: Instant, dogLover: DogLover, dog: Dog): Boolean

}