package org.modules.config;

import org.modules.domain.bo.UserBO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;

/**
 * The type EntityConfigure.
 *
 * @author Jx-zou
 */
@EnableR2dbcAuditing
@Configuration
public class EntityConfigure {

    @Bean
    public ReactiveAuditorAware<Long> accountReactiveAuditorAware() {
        return () -> ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(UserBO.class::cast)
                .mapNotNull(userBO -> userBO.getAccount().getId());
    }
}
