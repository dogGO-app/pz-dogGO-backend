package pl.put.poznan.pz.doggo.modules.mapmarker

import org.springframework.web.bind.annotation.*
import pl.put.poznan.pz.doggo.modules.auth.dto.mapmarker.MapMarkerDTO
import javax.validation.Valid

@RestController
@RequestMapping("/api/mapMarkers")
class MapMarkerController(
        private val mapMarkerService: MapMarkerService
) {

    @PostMapping
    fun saveMapMarker(@Valid @RequestBody mapMarkerDTO: MapMarkerDTO): MapMarkerDTO {
        return mapMarkerService.saveMapMarker(mapMarkerDTO)
    }

    @GetMapping
    fun getAllMapMarkers(): List<MapMarkerDTO> {
        return mapMarkerService.getAllMapMarkers()
    }
}