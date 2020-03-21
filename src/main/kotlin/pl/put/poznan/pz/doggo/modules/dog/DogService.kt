package pl.put.poznan.pz.doggo.modules.dog

import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.infrastructure.exceptions.DogAlreadyExistsException
import pl.put.poznan.pz.doggo.infrastructure.exceptions.DogNotFoundException
import pl.put.poznan.pz.doggo.modules.auth.dto.dog.DogDTO
import pl.put.poznan.pz.doggo.modules.auth.user.UserEntity
import java.util.*

@Service
class DogService(
        private val dogRepository: DogRepository
) {

    fun addDog(dogDTO: DogDTO, user: UserEntity): DogDTO {
        checkIfDogExists(dogDTO.name, user.id)
        val dog = Dog(
                name = dogDTO.name,
                breed = dogDTO.breed,
                color = dogDTO.color,
                description = dogDTO.description,
                lastVaccinationDate = dogDTO.lastVaccinationDate,
                user = user
        )
        return DogDTO(dogRepository.save(dog))
    }

    fun updateDog(dogDTO: DogDTO, user: UserEntity): DogDTO {
        val dog = dogRepository.findByNameAndUserId(dogDTO.name, user.id)
                ?: throw DogNotFoundException(dogDTO.name, user.id)
        val updatedDog = Dog(
                id = dog.id,
                name = dog.name,
                breed = dogDTO.breed,
                color = dogDTO.color,
                description = dogDTO.description,
                lastVaccinationDate = dogDTO.lastVaccinationDate,
                user = user
        )
        return DogDTO(dogRepository.save(updatedDog))
    }

    fun getUserDogs(user: UserEntity): List<DogDTO> {
        return dogRepository.findAllByUserId(user.id).map { DogDTO(it) }
    }

    fun getDog(name: String, user: UserEntity): DogDTO {
        return DogDTO(dogRepository.findByNameAndUserId(name, user.id)
                ?: throw DogNotFoundException(name, user.id))
    }

    private fun checkIfDogExists(name: String, userId: UUID) {
        if (dogRepository.existsByNameAndUserId(name, userId))
            throw DogAlreadyExistsException(name, userId)
    }
}