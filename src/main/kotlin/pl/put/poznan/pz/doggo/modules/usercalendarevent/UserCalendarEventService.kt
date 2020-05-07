package pl.put.poznan.pz.doggo.modules.usercalendarevent

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.infrastructure.exceptions.UserCalendarEventAlreadyExistsException
import pl.put.poznan.pz.doggo.infrastructure.exceptions.UserCalendarEventDateTimeException
import pl.put.poznan.pz.doggo.infrastructure.exceptions.UserCalendarEventNotFoundException
import pl.put.poznan.pz.doggo.infrastructure.exceptions.UserCalendarIdEmptyException
import pl.put.poznan.pz.doggo.modules.usercalendarevent.dto.UserCalendarEventDTO
import pl.put.poznan.pz.doggo.modules.dog.Dog
import pl.put.poznan.pz.doggo.modules.dog.DogService
import pl.put.poznan.pz.doggo.modules.doglover.DogLover
import java.time.*
import java.util.*

@Service
class UserCalendarEventService(
        @Value("\${self.time-zone}") private val timeZone: String,
        private val userCalendarEventRepository: UserCalendarEventRepository,
        private val dogService: DogService
) {

    fun getUserCalendar(dogLover: DogLover): List<UserCalendarEventDTO> {
        return userCalendarEventRepository.findAllByDogLoverAndDateTimeAfter(dogLover, Instant.now())
                .sortedBy { it.dateTime }
                .map { UserCalendarEventDTO(it, timeZone) }
    }

    fun saveCalendarEvent(userCalendarEventDTO: UserCalendarEventDTO, dogLover: DogLover): UserCalendarEventDTO {
        val dog = dogService.getDogEntity(userCalendarEventDTO.dogName, dogLover)
        val dateTime = getInstantFromDateAndTime(userCalendarEventDTO.date, userCalendarEventDTO.time)
        checkIfDateTimeIsAfterNowOrThrow(dateTime)
        checkIfEventAlreadyExists(dateTime, dogLover, dog)
        val userCalendarEvent = UserCalendarEvent(
                dateTime = dateTime,
                description = userCalendarEventDTO.description,
                dog = dog,
                dogLover = dogLover
        )

        return UserCalendarEventDTO(userCalendarEventRepository.save(userCalendarEvent), timeZone)
    }

    fun updateCalendarEvent(userCalendarEventDTO: UserCalendarEventDTO, dogLover: DogLover): UserCalendarEventDTO {
        val calendarEvent = getCalendarEventEntity(userCalendarEventDTO.id
                ?: throw UserCalendarIdEmptyException(), dogLover)
        val dog = dogService.getDogEntity(userCalendarEventDTO.dogName, dogLover)
        val dateTime = getInstantFromDateAndTime(userCalendarEventDTO.date, userCalendarEventDTO.time)
        checkIfDateTimeIsAfterNowOrThrow(dateTime)
        checkIfEventAlreadyExists(dateTime, dogLover, dog)
        val updatedCalendarEvent = UserCalendarEvent(
                id = calendarEvent.id,
                dateTime = dateTime,
                description = userCalendarEventDTO.description,
                dog = dog,
                dogLover = dogLover
        )

        return UserCalendarEventDTO(userCalendarEventRepository.save(updatedCalendarEvent), timeZone)
    }

    fun getCalendarEvent(id: UUID, dogLover: DogLover): UserCalendarEventDTO {
        return UserCalendarEventDTO(userCalendarEventRepository.findByIdAndDogLover(id, dogLover)
                ?: throw UserCalendarEventNotFoundException(id, dogLover.id),
                timeZone)
    }

    private fun checkIfDateTimeIsAfterNowOrThrow(dateTime: Instant) {
        if (dateTime.isBefore(Instant.now()))
            throw UserCalendarEventDateTimeException()
    }

    private fun checkIfEventAlreadyExists(dateTime: Instant, dogLover: DogLover, dog: Dog) {
        if (userCalendarEventRepository.existsByDateTimeAndDogLoverAndDog(dateTime, dogLover, dog))
            throw UserCalendarEventAlreadyExistsException(dateTime, dogLover.id, dog.name)
    }

    private fun getCalendarEventEntity(id: UUID, dogLover: DogLover): UserCalendarEvent {
        return userCalendarEventRepository.findByIdAndDogLover(id, dogLover)
                ?: throw UserCalendarEventNotFoundException(id, dogLover.id)
    }

    private fun getInstantFromDateAndTime(date: LocalDate, time: LocalTime): Instant {
        return LocalDateTime.of(date, time).atZone(ZoneId.of(timeZone)).toInstant()
    }

}