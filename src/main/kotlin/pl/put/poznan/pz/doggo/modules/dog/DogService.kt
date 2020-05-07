package pl.put.poznan.pz.doggo.modules.dog

import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.infrastructure.exceptions.DogAlreadyExistsException
import pl.put.poznan.pz.doggo.infrastructure.exceptions.DogNotFoundException
import pl.put.poznan.pz.doggo.modules.dog.dto.DogDTO
import pl.put.poznan.pz.doggo.modules.doglover.DogLover
import java.util.*

@Service
class DogService(
        private val dogRepository: DogRepository
) {

    fun addDog(dogDTO: DogDTO, dogLover: DogLover): DogDTO {
        checkIfDogExists(dogDTO.name, dogLover.id)
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

    fun updateDog(dogDTO: DogDTO, dogLover: DogLover): DogDTO {
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

    fun getUserDogs(dogLover: DogLover): List<DogDTO> {
        return dogRepository.findAllByDogLoverId(dogLover.id).map { DogDTO(it) }
    }

    fun getDog(name: String, dogLover: DogLover): DogDTO {
        return DogDTO(dogRepository.findByNameAndDogLoverId(name, dogLover.id)
                ?: throw DogNotFoundException(name, dogLover.id))
    }

    fun getDogEntity(name: String, dogLover: DogLover): Dog {
        return dogRepository.findByNameAndDogLoverId(name, dogLover.id)
                ?: throw DogNotFoundException(name, dogLover.id)
    }

    private fun checkIfDogExists(name: String, dogLoverId: UUID) {
        if (dogRepository.existsByNameAndDogLoverId(name, dogLoverId))
            throw DogAlreadyExistsException(name, dogLoverId)
    }
}