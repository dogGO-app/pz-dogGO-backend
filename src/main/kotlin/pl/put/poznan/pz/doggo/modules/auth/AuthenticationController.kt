package pl.put.poznan.pz.doggo.modules.auth

import com.fasterxml.jackson.annotation.JsonView
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.put.poznan.pz.doggo.modules.auth.dto.requests.LoginRequestDTO
import pl.put.poznan.pz.doggo.modules.auth.dto.requests.SignUpRequestDTO
import pl.put.poznan.pz.doggo.modules.auth.dto.responses.JwtResponseDTO
import pl.put.poznan.pz.doggo.modules.auth.user.UserEntity
import pl.put.poznan.pz.doggo.views.Views
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
        private val authenticationService: AuthenticationService
) {

    @PostMapping("/signin")
    @JsonView(Views.Public::class)
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequestDTO): JwtResponseDTO {
        return authenticationService.authenticateUser(loginRequest)
    }

    @PostMapping("/signup")
    @JsonView(Views.Public::class)
    fun registerUser(@Valid @RequestBody signUpRequest: SignUpRequestDTO): UserEntity {
        return authenticationService.registerUser(signUpRequest)
    }
}