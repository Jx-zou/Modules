package org.modules.service.impl;

import org.modules.reactive.entity.Resource;
import org.modules.reactive.repository.ResourceRepository;
import org.modules.service.RequestMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.RequestMappingInfo;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type RequestMappingServiceImpl.
 *
 * @author Jx-zou
 */
@Service
public class RequestMappingServiceImpl implements RequestMappingService {

    private final ResourceRepository resourceRepository;

    @Autowired
    public RequestMappingServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Flux<Resource> handle(RequestMappingHandlerMapping handlerMapping) {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        List<Resource> resources = new ArrayList<>();
        for (RequestMappingInfo info : handlerMethods.keySet()) {
            Resource resource = new Resource();
            //设置API名
            resource.setName(info.getName());
            //设置METHOD
            StringBuilder methodBuilder = new StringBuilder();
            for (RequestMethod method : info.getMethodsCondition().getMethods()) {
                methodBuilder.append(method);
            }
            resource.setMethod(methodBuilder.toString());
            //设置pattern
            StringBuilder urlBuilder = new StringBuilder();
            for (PathPattern pattern : info.getPatternsCondition().getPatterns()) {
                urlBuilder.append(pattern);
            }
            resource.setPattern(urlBuilder.toString());
            resources.add(resource);
        }
        return resourceRepository.saveAll(resources);
    }
}
