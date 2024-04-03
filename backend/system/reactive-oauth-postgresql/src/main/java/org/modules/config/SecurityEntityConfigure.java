package org.modules.config;

import org.modules.domain.bo.UserBO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;

/**
 * The type EntityConfigure.
 *
 * @author Jx-zou
 */
@Configuration
public class SecurityEntityConfigure {

    @Bean
    public ReactiveAuditorAware<Long> accountReactiveAuditorAware() {
        return () -> ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(UserBO.class::cast)
                .mapNotNull(userBO -> userBO.getAccount().getId())
                .defaultIfEmpty(1L);
    }
}
