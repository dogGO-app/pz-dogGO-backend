package pl.put.poznan.pz.doggo.modules.usercalendarevent

import org.springframework.web.bind.annotation.*
import pl.put.poznan.pz.doggo.modules.auth.dto.usercalendarevent.UserCalendarEventDTO
import java.util.*

@RestController
@RequestMapping("/api/user-calendar-events")
class UserCalendarEventController(
        private val userCalendarEventService: UserCalendarEventService
) {

    @GetMapping
    fun getUserCalendar(): List<UserCalendarEventDTO> {
        return userCalendarEventService.getUserCalendar()
    }

    @GetMapping("/{id}")
    fun getUserCalendarEvent(@PathVariable id: UUID): UserCalendarEventDTO {
        return userCalendarEventService.getCalendarEvent(id)
    }

    @PostMapping
    fun saveCalendarEvent(@RequestBody userCalendarEventDTO: UserCalendarEventDTO): UserCalendarEventDTO {
        return userCalendarEventService.saveCalendarEvent(userCalendarEventDTO)
    }

    @PutMapping
    fun updateCalendarEvent(@RequestBody userCalendarEventDTO: UserCalendarEventDTO): UserCalendarEventDTO {
        return userCalendarEventService.updateCalendarEvent(userCalendarEventDTO)
    }
}