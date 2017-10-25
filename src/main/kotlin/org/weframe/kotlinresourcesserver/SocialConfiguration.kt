package org.weframe.kotlinresourcesserver

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.social.connect.web.SignInAdapter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.social.connect.Connection
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.User
import org.springframework.web.context.request.NativeWebRequest


@Configuration
class SocialConfiguration {

    @Bean
    fun authSignInAdapter(): SignInAdapter? {
        return SignInAdapter { s: String, connection: Connection<*>, _: NativeWebRequest ->
            val facebook: Facebook = connection.api as Facebook
            val userProfile = facebook.fetchObject("me", User::class.java, "id", "email", "first_name", "last_name", "name")
            val username = userProfile.name
            val authentication = UsernamePasswordAuthenticationToken(username, null, null)
            SecurityContextHolder.getContext().authentication = authentication
            return@SignInAdapter null
        }
    }



}