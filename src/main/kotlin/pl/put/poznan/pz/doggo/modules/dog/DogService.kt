package pl.put.poznan.pz.doggo.modules.dog

import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.infrastructure.exceptions.DogAlreadyExistsException
import pl.put.poznan.pz.doggo.infrastructure.exceptions.DogNotFoundException
import pl.put.poznan.pz.doggo.infrastructure.exceptions.UserAlreadyHasDogException
import pl.put.poznan.pz.doggo.modules.auth.dto.dog.DogDTO
import pl.put.poznan.pz.doggo.modules.auth.security.authorization.AuthorizationService
import java.util.*

@Service
class DogService(
        private val dogRepository: DogRepository,
        private val authorizationService: AuthorizationService
) {

    fun addDog(dogDTO: DogDTO): DogDTO {
        val dogLover = authorizationService.getCurrentDogLover()
        checkIfUserHasDog(dogLover.id)
        val dog = Dog(
                name = dogDTO.name,
                breed = dogDTO.breed,
                color = dogDTO.color,
                description = dogDTO.description,
                lastVaccinationDate = dogDTO.lastVaccinationDate,
                dogLover = dogLover
        )
        return DogDTO(dogRepository.save(dog))
    }

    fun updateDog(dogDTO: DogDTO): DogDTO {
        val dogLover = authorizationService.getCurrentDogLover()
        val dog = dogRepository.findByNameAndDogLoverId(dogDTO.name, dogLover.id)
                ?: throw DogNotFoundException(dogDTO.name, dogLover.id)
        val updatedDog = Dog(
                id = dog.id,
                name = dog.name,
                breed = dogDTO.breed,
                color = dogDTO.color,
                description = dogDTO.description,
                lastVaccinationDate = dogDTO.lastVaccinationDate,
                dogLover = dogLover
        )
        return DogDTO(dogRepository.save(updatedDog))
    }

    fun getUserDogs(): DogDTO {
        val dogLover = authorizationService.getCurrentDogLover()
        return DogDTO(dogRepository.findAllByDogLoverId(dogLover.id).first())
    }

    fun getDog(name: String): DogDTO {
        val dogLover = authorizationService.getCurrentDogLover()
        return DogDTO(dogRepository.findByNameAndDogLoverId(name, dogLover.id)
                ?: throw DogNotFoundException(name, dogLover.id))
    }

    private fun checkIfDogExists(name: String, dogLoverId: UUID) {
        if (dogRepository.existsByNameAndDogLoverId(name, dogLoverId))
            throw DogAlreadyExistsException(name, dogLoverId)
    }

    private fun checkIfUserHasDog(dogLoverId: UUID) {
        if (dogRepository.findAllByDogLoverId(dogLoverId).isNotEmpty())
            throw UserAlreadyHasDogException(dogLoverId)
    }
}