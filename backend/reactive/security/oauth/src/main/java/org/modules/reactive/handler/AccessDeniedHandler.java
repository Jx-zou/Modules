package org.modules.reactive.handler;

import org.modules.reactive.util.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * The type AccessDeniedHandler.
 *
 * @author Jx-zou
 */
@Component
public class AccessDeniedHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        return ResponseUtils.jsonWriteAndFlushWith(exchange, new ResponseEntity<>("权限不足.", HttpStatus.BAD_REQUEST));
    }
}
