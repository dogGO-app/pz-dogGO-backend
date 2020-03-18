package pl.put.poznan.pz.doggo.modules.auth.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.put.poznan.pz.doggo.modules.auth.dto.UserDTO
import pl.put.poznan.pz.doggo.modules.auth.security.userdetails.CustomUserDetailsService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/test")
class TestController(
        private val userDetailsService: CustomUserDetailsService
) {

    @GetMapping
    fun authenticateUser(request: HttpServletRequest): UserDTO {
        return UserDTO(userDetailsService.getUser(request))
    }

}