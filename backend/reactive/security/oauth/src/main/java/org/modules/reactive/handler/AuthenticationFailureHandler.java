package org.modules.reactive.handler;

import org.modules.reactive.util.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * The type LoginFailureHandler.
 *
 * @author Jx-zou
 */
@Component
public class AuthenticationFailureHandler implements ServerAuthenticationFailureHandler {
    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        return ResponseUtils.jsonWriteAndFlushWith(webFilterExchange, new ResponseEntity<>("登录失败.", HttpStatus.NOT_ACCEPTABLE));
    }
}
