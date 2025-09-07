package se.moln.productservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import({org.springframework.boot.actuate.autoconfigure.endpoint.jackson.JacksonEndpointAutoConfiguration.class,
        AdminHealthControllerTest.ActuatorTestConfig.class})
class AdminHealthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HealthEndpoint healthEndpoint;

    @Autowired
    private InfoEndpoint infoEndpoint;

    @TestConfiguration
    static class ActuatorTestConfig {
        @Bean
        HealthEndpoint healthEndpoint() {
            return mock(HealthEndpoint.class);
        }

        @Bean
        InfoEndpoint infoEndpoint() {
            return mock(InfoEndpoint.class);
        }
    }


    @Test
    void healthDetails_Unauthorized_WhenNotAdmin() throws Exception {
        mockMvc.perform(get("/api/admin/health-details"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void healthDetails_ReturnsDetails_WhenAdmin() throws Exception {
        when(healthEndpoint.health()).thenReturn(Health.status(Status.UP).build());
        when(infoEndpoint.info()).thenReturn(java.util.Map.of("app", java.util.Map.of("name", "product-service")));

        MockMvc standaloneMvc = MockMvcBuilders
                .standaloneSetup(new AdminHealthController(healthEndpoint, infoEndpoint))
                .build();

        standaloneMvc.perform(get("/api/admin/health-details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.info.app.name").value("product-service"));
    }
}