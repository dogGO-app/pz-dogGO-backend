package pl.put.poznan.pz.doggo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DoggoApplication

fun main(args: Array<String>) {
    runApplication<DoggoApplication>(*args)
}
