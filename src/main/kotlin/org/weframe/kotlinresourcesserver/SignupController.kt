package org.weframe.kotlinresourcesserver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.social.connect.Connection
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.UsersConnectionRepository
import org.springframework.social.connect.web.ProviderSignInUtils
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.context.request.WebRequest

@Controller
class SignupController
@Autowired
constructor(connectionFactoryLocator: ConnectionFactoryLocator, connectionRepository: UsersConnectionRepository) {

    private val signInUtils: ProviderSignInUtils = ProviderSignInUtils(connectionFactoryLocator, connectionRepository)

    @RequestMapping(value = "/signup")
    fun signup(request: WebRequest): String {
        val connection = signInUtils.getConnectionFromSession(request)
        if (connection != null) {
            val facebook: Facebook = connection.api as Facebook
            val userProfile = facebook.fetchObject("me", User::class.java, "id", "email", "first_name", "last_name", "name")
            val username = userProfile.firstName + " " + userProfile.lastName
            val authentication = UsernamePasswordAuthenticationToken(username, null, null)
            SecurityContextHolder.getContext().authentication = authentication
            signInUtils.doPostSignUp(connection.displayName, request)
        }
        return "redirect:http://localhost:9000/"
    }
}
