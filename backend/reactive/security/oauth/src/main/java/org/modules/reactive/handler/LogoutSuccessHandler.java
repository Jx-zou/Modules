package org.modules.reactive.handler;

import org.modules.reactive.util.ResponseUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * The type LogoutSuccessHandler.
 *
 * @author Jx-zou
 */
@Component
public class LogoutSuccessHandler implements ServerLogoutSuccessHandler {
    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        return ResponseUtils.jsonWriteAndFlushWith(exchange, ResponseEntity.ok("注销成功."));
    }
}
