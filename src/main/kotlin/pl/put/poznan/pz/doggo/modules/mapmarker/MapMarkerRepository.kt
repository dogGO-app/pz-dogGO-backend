package pl.put.poznan.pz.doggo.modules.mapmarker

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MapMarkerRepository : JpaRepository<MapMarker, UUID>