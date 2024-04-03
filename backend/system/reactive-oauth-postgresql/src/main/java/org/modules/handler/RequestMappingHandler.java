package org.modules.handler;

import org.modules.reactive.enmus.DataStatus;
import org.modules.reactive.entity.Permission;
import org.modules.reactive.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.RequestMappingInfo;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type RequestMappingServiceImpl.
 *
 * @author Jx-zou
 */
@Component
public class RequestMappingHandler implements InitializeHandler {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;
    private final PermissionRepository permissionRepository;

    @Autowired
    public RequestMappingHandler(RequestMappingHandlerMapping requestMappingHandlerMapping, PermissionRepository permissionRepository) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void handle() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        List<Permission> permissionList = new ArrayList<>();
        for (RequestMappingInfo info : handlerMethods.keySet()) {
            Permission permission = new Permission();
            //设置API名
            permission.setName(info.getName());
            //设置METHOD
            StringBuilder methodBuilder = new StringBuilder();
            for (RequestMethod method : info.getMethodsCondition().getMethods()) {
                methodBuilder.append(method);
                methodBuilder.append(',');
            }
            int end = methodBuilder.length() - 1;
            if (methodBuilder.charAt(end) == ',') {
                methodBuilder.deleteCharAt(end);
            }
            //设置Method
            permission.setMethod(methodBuilder.toString());
            //设置pattern
            StringBuilder urlBuilder = new StringBuilder();
            for (PathPattern pattern : info.getPatternsCondition().getPatterns()) {
                urlBuilder.append(pattern);
            }
            permission.setPattern(urlBuilder.toString());
            //设置权限接口状态
            permission.setStatus(DataStatus.NORMAL.getStatus());
            //设置说明,默认是该接口名字
            permission.setComments(info.getName());
            permissionList.add(permission);
        }
        permissionRepository.saveAll(permissionList).collectList().block();
    }
}
