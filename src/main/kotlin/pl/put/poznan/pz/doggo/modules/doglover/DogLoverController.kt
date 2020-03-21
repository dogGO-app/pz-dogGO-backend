package pl.put.poznan.pz.doggo.modules.doglover

import org.springframework.web.bind.annotation.*
import pl.put.poznan.pz.doggo.modules.doglover.dto.DogLoverProfileDTO
import javax.validation.Valid

@RestController
@RequestMapping("api/dogLover")
class DogLoverController(
        private val dogLoverService: DogLoverService
) {
    @GetMapping
    fun getDogLoverProfile(): DogLoverProfileDTO =
            dogLoverService.getDogLoverProfile()

    @PostMapping
    fun createDogLoverProfile(@Valid @RequestBody dogLoverProfile: DogLoverProfileDTO): DogLoverProfileDTO =
            dogLoverService.createDogLoverProfile(dogLoverProfile)

    @PutMapping
    fun editDogLoverProfile(@Valid @RequestBody dogLoverProfile: DogLoverProfileDTO): DogLoverProfileDTO =
            dogLoverService.editDogLoverProfile(dogLoverProfile)
}