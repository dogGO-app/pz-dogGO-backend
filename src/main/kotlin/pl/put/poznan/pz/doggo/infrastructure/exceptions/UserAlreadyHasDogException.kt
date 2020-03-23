package pl.put.poznan.pz.doggo.infrastructure.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@ResponseStatus(HttpStatus.CONFLICT)
class UserAlreadyHasDogException(userId: UUID) :
        RuntimeException("User with id: $userId already has dog.")