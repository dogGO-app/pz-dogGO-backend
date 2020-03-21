package pl.put.poznan.pz.doggo.modules.doglover

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.infrastructure.exceptions.DogLoverAlreadyExistsException
import pl.put.poznan.pz.doggo.infrastructure.exceptions.DogLoverNotFoundException
import pl.put.poznan.pz.doggo.modules.auth.security.authorization.AuthorizationService
import pl.put.poznan.pz.doggo.modules.doglover.dto.DogLoverProfileDTO

@Service
class DogLoverService(
        private val dogLoverRepository: DogLoverRepository,
        private val authorizationService: AuthorizationService
) {
    fun getDogLoverProfile(): DogLoverProfileDTO {
        val user = authorizationService.getCurrentUser()
        return DogLoverProfileDTO(
                dogLoverRepository.findByIdOrNull(user.id) ?: throw DogLoverNotFoundException()
        )
    }

    fun createDogLoverProfile(dogLoverProfile: DogLoverProfileDTO): DogLoverProfileDTO {
        val user = authorizationService.getCurrentUser()
        if (dogLoverRepository.existsById(user.id))
            throw DogLoverAlreadyExistsException()

        return DogLoverProfileDTO(
                dogLoverRepository.save(dogLoverProfile.toDogLoverEntity(user))
        )
    }

    fun editDogLoverProfile(dogLoverProfile: DogLoverProfileDTO): DogLoverProfileDTO {
        val user = authorizationService.getCurrentUser()
        if (!dogLoverRepository.existsById(user.id))
            throw DogLoverNotFoundException()

        return DogLoverProfileDTO(
                dogLoverRepository.save(dogLoverProfile.toDogLoverEntity(user))
        )
    }
}