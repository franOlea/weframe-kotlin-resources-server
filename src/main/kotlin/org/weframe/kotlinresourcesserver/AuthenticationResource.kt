package org.weframe.kotlinresourcesserver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession
import org.springframework.web.bind.annotation.RequestMethod
import java.security.Principal


@RestController
@RequestMapping("/api/session")
class AuthenticationResource @Autowired constructor(@Suppress("unused") val authenticationManager: AuthenticationManager) {

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun session(user: Principal?): ResponseEntity<String> {
        return if(user != null) {
            ResponseEntity.ok(user.name)
        } else {
            ResponseEntity.ok("NO_USER")
        }
    }

    @RequestMapping(method = arrayOf(RequestMethod.DELETE))
    fun logout(session: HttpSession) {
        session.invalidate()
    }

}