package org.modules.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * The type PermitController.
 *
 * @author Jx-zou
 */
@RestController
@RequestMapping("permit")
public class PermitController {

    @GetMapping(name = "test1", value = "hello")
    public Mono<ResponseEntity<String>> hello() {
        return Mono.just(ResponseEntity.ok("Hello!"));
    }

    @PostMapping(name = "test1", value = "test1")
    public Mono<ResponseEntity<String>> test1(@RequestParam("value") String value) {
        return Mono.just(ResponseEntity.ok(value));
    }

    @PostMapping(name = "test2", value = "test2/{value}")
    public Mono<ResponseEntity<String>> test2(@PathVariable("value") String value) {
        return Mono.just(ResponseEntity.ok(value));
    }
}
