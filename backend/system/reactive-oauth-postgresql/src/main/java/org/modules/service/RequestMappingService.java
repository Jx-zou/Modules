package org.modules.service;

import org.modules.reactive.entity.Resource;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import reactor.core.publisher.Flux;

/**
 * The type RequestMappingHandler.
 *
 * @author Jx-zou
 */
public interface RequestMappingService {

    Flux<Resource> handle(RequestMappingHandlerMapping handlerMapping);
}
