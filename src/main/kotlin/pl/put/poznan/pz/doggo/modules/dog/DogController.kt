package pl.put.poznan.pz.doggo.modules.dog

import org.springframework.web.bind.annotation.*
import pl.put.poznan.pz.doggo.modules.auth.dto.dog.DogDTO
import pl.put.poznan.pz.doggo.modules.auth.security.authorization.AuthorizationService

@RestController
@RequestMapping("/api/dogs")
class DogController(
        private val dogService: DogService,
        private val authorizationService: AuthorizationService
) {

    @PostMapping
    fun addDog(@RequestBody dog: DogDTO): DogDTO {
        return dogService.addDog(dog, authorizationService.getCurrentUser())
    }

    @PutMapping
    fun updateDog(@RequestBody dog: DogDTO): DogDTO {
        return dogService.updateDog(dog, authorizationService.getCurrentUser())
    }

    @GetMapping("/{name}")
    fun getDog(@PathVariable name: String): DogDTO {
        return dogService.getDog(name, authorizationService.getCurrentUser())
    }

    @GetMapping
    fun getUserDogs(): List<DogDTO> {
        return dogService.getUserDogs(authorizationService.getCurrentUser())
    }
}
