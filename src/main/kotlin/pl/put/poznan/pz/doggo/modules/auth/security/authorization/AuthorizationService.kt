package pl.put.poznan.pz.doggo.modules.auth.security.authorization

import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import pl.put.poznan.pz.doggo.infrastructure.exceptions.DogLoverNotFoundException
import pl.put.poznan.pz.doggo.modules.auth.security.userdetails.CustomUserDetails
import pl.put.poznan.pz.doggo.modules.auth.user.UserEntity
import pl.put.poznan.pz.doggo.modules.doglover.DogLover
import pl.put.poznan.pz.doggo.modules.doglover.DogLoverRepository

@Service
class AuthorizationService(
        private val dogLoverRepository: DogLoverRepository
) {
    fun getCurrentUser(): UserEntity {
        val authentication = SecurityContextHolder.getContext().authentication
        return (authentication.principal as CustomUserDetails).userEntity
    }

    fun getCurrentDogLover(): DogLover {
        val user = getCurrentUser()
        return dogLoverRepository.findByIdOrNull(user.id) ?: throw DogLoverNotFoundException()
    }
}