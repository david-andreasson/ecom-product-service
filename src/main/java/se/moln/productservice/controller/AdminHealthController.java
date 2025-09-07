package se.moln.productservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Admin Health", description = "Rich health information for administrators")
@ConditionalOnBean({HealthEndpoint.class, InfoEndpoint.class})
public class AdminHealthController {

    private final HealthEndpoint healthEndpoint;
    private final InfoEndpoint infoEndpoint;

    public AdminHealthController(HealthEndpoint healthEndpoint, InfoEndpoint infoEndpoint) {
        this.healthEndpoint = healthEndpoint;
        this.infoEndpoint = infoEndpoint;
    }

    @GetMapping("/api/admin/health-details")
    @Operation(summary = "Detailed health information (admin only)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @SecurityRequirement(name = "basicAuth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> healthDetails() {
        Map<String, Object> body = new HashMap<>();
        HealthComponent health = healthEndpoint.health();
        Map<String, Object> info = infoEndpoint.info();
        body.put("status", health.getStatus().getCode());
        body.put("health", health);
        body.put("info", info);
        return ResponseEntity.ok(body);
    }
}