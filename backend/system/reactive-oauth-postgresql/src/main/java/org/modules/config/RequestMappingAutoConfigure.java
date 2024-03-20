package org.modules.config;

import org.modules.service.RequestMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;

/**
 * The type RequestMappingAutoConfigure.
 *
 * @author Jx-zou
 */
@Configuration
public class RequestMappingAutoConfigure {

    private final ApplicationContext applicationContext;
    private final RequestMappingHandlerMapping mappingHandlerMapping;

    @Autowired
    public RequestMappingAutoConfigure(ApplicationContext applicationContext, RequestMappingHandlerMapping mappingHandlerMapping) {
        this.applicationContext = applicationContext;
        this.mappingHandlerMapping = mappingHandlerMapping;
        this.initialize();
    }

    private void initialize() {
        RequestMappingService requestMappingService = applicationContext.getBean(RequestMappingService.class);
        requestMappingService.handle(mappingHandlerMapping).collectList().blockOptional();
    }
}
