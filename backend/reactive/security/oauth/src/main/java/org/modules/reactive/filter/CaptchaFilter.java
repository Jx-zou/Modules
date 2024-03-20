package org.modules.reactive.filter;

import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * The type CustomSwitchUserFilter.
 *
 * @author Jx-zou
 */
@Component
public class CaptchaFilter implements WebFilter {

    private final ServerWebExchangeMatcher switchUserMatcher = createMatcher("/login/impersonate");

    @NonNull
    @Override
    public Mono<Void> filter(@Nullable ServerWebExchange exchange, @Nullable WebFilterChain chain) {
        final WebFilterExchange webFilterExchange = new WebFilterExchange(exchange, chain);

        return chain.filter(exchange);
    }

    private static ServerWebExchangeMatcher createMatcher(String pattern) {
        return ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, pattern);
    }
}
