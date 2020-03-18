package pl.put.poznan.pz.doggo.modules.auth.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.put.poznan.pz.doggo.modules.auth.dto.user.UserDTO
import pl.put.poznan.pz.doggo.modules.auth.security.authorization.AuthorizationService

@RestController
@RequestMapping("/api/test")
class TestController(
        private val authorizationService: AuthorizationService
) {

    @GetMapping
    fun authenticateUser(): UserDTO {
        val user = authorizationService.getCurrentUser()
        return UserDTO(user)
    }

}