package pl.put.poznan.pz.doggo.modules.usercalendarevent

import org.springframework.web.bind.annotation.*
import pl.put.poznan.pz.doggo.modules.usercalendarevent.dto.UserCalendarEventDTO
import pl.put.poznan.pz.doggo.modules.auth.security.authorization.AuthorizationService
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/userCalendarEvents")
class UserCalendarEventController(
        private val userCalendarEventService: UserCalendarEventService,
        private val authorizationService: AuthorizationService
) {

    @GetMapping
    fun getUserCalendar(): List<UserCalendarEventDTO> {
        return userCalendarEventService.getUserCalendar(authorizationService.getCurrentDogLover())
    }

    @GetMapping("/{id}")
    fun getUserCalendarEvent(@PathVariable id: UUID): UserCalendarEventDTO {
        return userCalendarEventService.getCalendarEvent(id, authorizationService.getCurrentDogLover())
    }

    @PostMapping
    fun saveCalendarEvent(@Valid @RequestBody userCalendarEventDTO: UserCalendarEventDTO): UserCalendarEventDTO {
        return userCalendarEventService.saveCalendarEvent(userCalendarEventDTO, authorizationService.getCurrentDogLover())
    }

    @PutMapping
    fun updateCalendarEvent(@Valid @RequestBody userCalendarEventDTO: UserCalendarEventDTO): UserCalendarEventDTO {
        return userCalendarEventService.updateCalendarEvent(userCalendarEventDTO, authorizationService.getCurrentDogLover())
    }
}