package pl.put.poznan.pz.doggo.infrastructure.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserCalendarEventNotFoundException(id: UUID, userId: UUID) :
        RuntimeException("Calendar event with id: $id not exists for user with id: $userId.")