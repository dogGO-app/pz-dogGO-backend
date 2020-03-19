package pl.put.poznan.pz.doggo.infrastructure.exceptions

import io.jsonwebtoken.JwtException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class TokenRevokedException : JwtException("Token has been already revoked.")