package pl.put.poznan.pz.doggo.modules.doglover

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.infrastructure.exceptions.DogLoverNotFoundException
import pl.put.poznan.pz.doggo.modules.auth.user.UserEntity
import pl.put.poznan.pz.doggo.modules.doglover.dto.DogLoverProfileDTO

@Service
class DogLoverService(
        private val dogLoverRepository: DogLoverRepository
) {
    fun getDogLoverProfile(user: UserEntity): DogLoverProfileDTO {
        return DogLoverProfileDTO(
                dogLoverRepository.findByIdOrNull(user.id) ?: throw DogLoverNotFoundException()
        )
    }

    fun updateDogLoverProfile(dogLoverProfile: DogLoverProfileDTO, user: UserEntity): DogLoverProfileDTO {
        return DogLoverProfileDTO(
                dogLoverRepository.save(dogLoverProfile.toDogLoverEntity(user))
        )
    }
}