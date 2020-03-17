package pl.put.poznan.pz.doggo.modules.auth

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.put.poznan.pz.doggo.modules.auth.dto.LoginRequestDTO
import pl.put.poznan.pz.doggo.modules.auth.dto.SignUpRequestDTO
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {
//    @PostMapping("/signin")
//    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequestDTO): JwtResponseDTO {
//
//    }

//    @PostMapping("/signup")
//    fun registerUser(@Valid @RequestBody signUpRequest: SignUpRequestDTO): SignUpResponseDTO {
//
//    }
}