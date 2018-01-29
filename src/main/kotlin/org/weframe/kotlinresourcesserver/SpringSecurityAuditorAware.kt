package org.weframe.kotlinresourcesserver

import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import java.security.Principal

/**
 * Auditor implementation for getting user name for some
 * JPA repositories that use @CreatedBy annotations.
 */
class SpringSecurityAuditorAware : AuditorAware<String> {

    override fun getCurrentAuditor(): String? {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication == null || !authentication.isAuthenticated) {
            return null
        }
        return (authentication.principal as Principal).name
    }

}