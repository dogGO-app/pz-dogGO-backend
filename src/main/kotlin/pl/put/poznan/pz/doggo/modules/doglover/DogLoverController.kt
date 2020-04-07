package pl.put.poznan.pz.doggo.modules.doglover

import org.springframework.web.bind.annotation.*
import pl.put.poznan.pz.doggo.modules.auth.security.authorization.AuthorizationService
import pl.put.poznan.pz.doggo.modules.doglover.dto.DogLoverProfileDTO
import javax.validation.Valid

@RestController
@RequestMapping("api/dogLover")
class DogLoverController(
        private val dogLoverService: DogLoverService,
        private val authorizationService: AuthorizationService
) {
    @GetMapping
    fun getDogLoverProfile(): DogLoverProfileDTO =
            dogLoverService.getDogLoverProfile(authorizationService.getCurrentUser())

    @PutMapping
    fun updateDogLoverProfile(@Valid @RequestBody dogLoverProfile: DogLoverProfileDTO): DogLoverProfileDTO =
            dogLoverService.updateDogLoverProfile(dogLoverProfile, authorizationService.getCurrentUser())
}