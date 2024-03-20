package org.modules.reactive.handler;

import org.modules.reactive.util.ResponseUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * The type LoginSuccessHandler.
 *
 * @author Jx-zou
 */
@Component
public class AuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        return ResponseUtils.jsonWriteAndFlushWith(webFilterExchange, ResponseEntity.ok("登录成功."));
    }
}
