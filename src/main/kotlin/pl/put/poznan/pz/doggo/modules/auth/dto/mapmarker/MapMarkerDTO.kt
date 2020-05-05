package pl.put.poznan.pz.doggo.modules.auth.dto.mapmarker

import pl.put.poznan.pz.doggo.modules.mapmarker.MapMarker
import java.util.*
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin

data class MapMarkerDTO(
        val id: UUID,

        val description: String,

        @field:DecimalMin("-90.0")
        @field:DecimalMax("90.0")
        val latitude: Double,

        @field:DecimalMin("-180.0")
        @field:DecimalMax("180.0")
        val longitude: Double
) {
    constructor(mapMarker: MapMarker) : this(
            id = mapMarker.id,
            description = mapMarker.description,
            latitude = mapMarker.latitude,
            longitude = mapMarker.longitude
    )
}