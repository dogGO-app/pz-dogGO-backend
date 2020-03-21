package pl.put.poznan.pz.doggo.infrastructure.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@ResponseStatus(HttpStatus.CONFLICT)
class DogAlreadyExistsException(name: String, userId: UUID) :
        RuntimeException("Dog with name: $name already exists for user with id: $userId.")