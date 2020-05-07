package pl.put.poznan.pz.doggo.modules.dog.dto

import pl.put.poznan.pz.doggo.modules.dog.Dog
import java.time.LocalDate

data class DogDTO(
        val name: String,

        val breed: String?,

        val color: String?,

        val description: String?,

        val lastVaccinationDate: LocalDate?
) {

    constructor(dog: Dog) : this(
            name = dog.name,
            breed = dog.breed,
            color = dog.color,
            description = dog.description,
            lastVaccinationDate = dog.lastVaccinationDate
    )
}
