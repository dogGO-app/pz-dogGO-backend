package pl.put.poznan.pz.doggo.modules.auth.security.authentication

import org.springframework.web.bind.annotation.*
import pl.put.poznan.pz.doggo.modules.auth.dto.requests.LoginRequestDTO
import pl.put.poznan.pz.doggo.modules.auth.dto.requests.SignUpRequestDTO
import pl.put.poznan.pz.doggo.modules.auth.dto.responses.JwtTokenInfoDTO
import pl.put.poznan.pz.doggo.modules.auth.dto.user.UserInfoDTO
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
        private val authenticationService: AuthenticationService
) {

    @PostMapping("/signin")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequestDTO): JwtTokenInfoDTO {
        return authenticationService.authenticateUser(loginRequest)
    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody signUpRequest: SignUpRequestDTO): UserInfoDTO {
        return authenticationService.registerUser(signUpRequest)
    }

    @PostMapping("/signout")
    fun signOutUser(@RequestHeader("Authorization") authHeader: String): String {
        return authenticationService.signOutUser(authHeader)
    }
}