package pl.put.poznan.pz.doggo.modules.dog

import org.springframework.web.bind.annotation.*
import pl.put.poznan.pz.doggo.modules.auth.dto.dog.DogDTO

@RestController
@RequestMapping("/api/dogs")
class DogController(
        private val dogService: DogService
) {

    @PostMapping
    fun addDog(@RequestBody dog: DogDTO): DogDTO {
        return dogService.addDog(dog)
    }

    @PutMapping
    fun updateDog(@RequestBody dog: DogDTO): DogDTO {
        return dogService.updateDog(dog)
    }

    @GetMapping("/{name}")
    fun getDog(@PathVariable name: String): DogDTO {
        return dogService.getDog(name)
    }

    @GetMapping
    fun getUserDogs(): DogDTO {
        return dogService.getUserDogs()
    }
}
