package org.modules.controller;

import org.modules.domain.bo.UserBO;
import org.modules.reactive.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * The type AuthController.
 *
 * @author Jx-zou
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    @PreAuthorize(value = "hasPermission('getUserInfo')")
    @GetMapping(name = "getUserInfo", value = "/user/{id}")
    public Mono<ResponseEntity<UserBO>> getUserInfo(@PathVariable Long id) {
        Account account = new Account();
        account.setId(id);
        return Mono.just(ResponseEntity.ok(new UserBO(account)));
    }
}
