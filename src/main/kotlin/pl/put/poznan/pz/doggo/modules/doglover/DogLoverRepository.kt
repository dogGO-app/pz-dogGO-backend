package pl.put.poznan.pz.doggo.modules.doglover

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DogLoverRepository : JpaRepository<DogLover, UUID>