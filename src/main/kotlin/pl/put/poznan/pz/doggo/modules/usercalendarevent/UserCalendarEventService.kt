package pl.put.poznan.pz.doggo.modules.usercalendarevent

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.infrastructure.exceptions.CalendarIdEmptyException
import pl.put.poznan.pz.doggo.infrastructure.exceptions.UserCalendarEventNotFoundException
import pl.put.poznan.pz.doggo.modules.auth.dto.usercalendarevent.UserCalendarEventDTO
import pl.put.poznan.pz.doggo.modules.auth.security.authorization.AuthorizationService
import pl.put.poznan.pz.doggo.modules.dog.DogService
import pl.put.poznan.pz.doggo.modules.doglover.DogLover
import java.time.*
import java.util.*

@Service
class UserCalendarEventService(
        @Value("\${self.time-zone}") private val timeZone: String,
        private val userCalendarEventRepository: UserCalendarEventRepository,
        private val dogService: DogService,
        private val authorizationService: AuthorizationService
) {

    fun getUserCalendar(): List<UserCalendarEventDTO> {
        val dogLover = authorizationService.getCurrentDogLover()
        return userCalendarEventRepository.findAllByDogLoverAndDateTimeAfter(dogLover, Instant.now())
                .sortedBy { it.dateTime }
                .map { UserCalendarEventDTO(it, timeZone) }
    }

    fun saveCalendarEvent(userCalendarEventDTO: UserCalendarEventDTO): UserCalendarEventDTO {
        val dogLover = authorizationService.getCurrentDogLover()
        val dog = dogService.getDogEntity(userCalendarEventDTO.dogName)
        val dateTime = getInstantFromDateAndTime(userCalendarEventDTO.date, userCalendarEventDTO.time)
        val userCalendarEvent = UserCalendarEvent(
                dateTime = dateTime,
                description = userCalendarEventDTO.description,
                dog = dog,
                dogLover = dogLover
        )

        return UserCalendarEventDTO(userCalendarEventRepository.save(userCalendarEvent), timeZone)
    }

    fun updateCalendarEvent(userCalendarEventDTO: UserCalendarEventDTO): UserCalendarEventDTO {
        val dogLover = authorizationService.getCurrentDogLover()
        val calendarEvent = getCalendarEvent(userCalendarEventDTO.id ?: throw CalendarIdEmptyException(), dogLover)
        val dog = dogService.getDogEntity(userCalendarEventDTO.dogName)
        val dateTime = getInstantFromDateAndTime(userCalendarEventDTO.date, userCalendarEventDTO.time)
        val updatedCalendarEvent = UserCalendarEvent(
                id = calendarEvent.id,
                dateTime = dateTime,
                description = userCalendarEventDTO.description,
                dog = dog,
                dogLover = dogLover
        )

        return UserCalendarEventDTO(userCalendarEventRepository.save(updatedCalendarEvent), timeZone)
    }

    fun getCalendarEvent(id: UUID): UserCalendarEventDTO {
        val dogLover = authorizationService.getCurrentDogLover()
        return UserCalendarEventDTO(userCalendarEventRepository.findByIdAndDogLover(id, dogLover)
                ?: throw UserCalendarEventNotFoundException(id, dogLover.id),
                timeZone)
    }

    private fun getCalendarEvent(id: UUID, dogLover: DogLover): UserCalendarEvent {
        return userCalendarEventRepository.findByIdAndDogLover(id, dogLover)
                ?: throw UserCalendarEventNotFoundException(id, dogLover.id)
    }

    private fun getInstantFromDateAndTime(date: LocalDate, time: LocalTime): Instant {
        return LocalDateTime.of(date, time).atZone(ZoneId.of(timeZone)).toInstant()
    }

}