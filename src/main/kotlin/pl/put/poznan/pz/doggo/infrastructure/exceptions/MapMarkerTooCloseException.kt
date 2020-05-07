package pl.put.poznan.pz.doggo.infrastructure.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class MapMarkerTooCloseException(latitude: Double, longitude: Double) :
        RuntimeException("Map marker with latitude: $latitude and longitude: $longitude is too close!")