package pl.put.poznan.pz.doggo.infrastructure.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.Instant
import java.util.*

@ResponseStatus(HttpStatus.CONFLICT)
class UserCalendarEventAlreadyExistsException(dateTime: Instant, userId: UUID, dogName: String) :
        RuntimeException("Calendar event with date and time $dateTime, userId $userId and dog name $dogName already exists.")