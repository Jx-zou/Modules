package org.modules.reactive.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

/**
 * GlobalExceptionHandler
 *
 * @author jx
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public Mono<ResponseEntity<String>> AuthenticationCredentialsNotFoundHandler(AuthenticationCredentialsNotFoundException e) {
        log.error(e.getMessage(), e);
        return Mono.just(new ResponseEntity<>("认证失败.", HttpStatus.PRECONDITION_REQUIRED));
    }

    /**
     * Handle exception response result.
     *
     * @param e 异常
     * @return 响应实例
     */
    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<String>> handleException(Exception e){
        log.error(e.getMessage());
        return Mono.just(ResponseEntity.notFound().build());
    }
}
