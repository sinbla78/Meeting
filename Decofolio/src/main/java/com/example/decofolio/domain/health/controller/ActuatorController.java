package com.example.decofolio.domain.health.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator")
@Tag(name = "Actuator", description = "Spring Actuator Endpoints")
public class ActuatorController {

    // health 엔드포인트를 Swagger UI에 노출
    @Operation(summary = "서버 상태 체크", description = "Check the health of the application")
    @GetMapping("/health")
    public Health health() {
        return Health.up().build();  // 간단한 건강 상태 반환
    }
}
