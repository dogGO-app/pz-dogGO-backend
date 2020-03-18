package pl.put.poznan.pz.doggo.modules.auth.user

import com.fasterxml.jackson.annotation.JsonView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.put.poznan.pz.doggo.modules.auth.security.userdetails.CustomUserDetailsService
import pl.put.poznan.pz.doggo.views.Views
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/test")
class TestController(
        private val userDetailsService: CustomUserDetailsService
) {

    @GetMapping
    @JsonView(Views.Public::class)
    fun authenticateUser(request: HttpServletRequest): UserEntity {
        return userDetailsService.getUser(request)
    }

}