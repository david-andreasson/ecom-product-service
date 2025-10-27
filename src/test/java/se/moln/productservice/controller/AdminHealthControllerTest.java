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
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

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

    private static final class FixedObjectProvider<T> implements ObjectProvider<T> {
        private final T value;

        FixedObjectProvider(T value) {
            this.value = value;
        }

        @Override
        public T getObject(Object... args) {
            return value;
        }

        @Override
        public T getObject() {
            return value;
        }

        @Override
        public T getIfAvailable() {
            return value;
        }

        @Override
        public T getIfAvailable(Supplier<T> defaultSupplier) {
            return value != null ? value : defaultSupplier.get();
        }

        @Override
        public void ifAvailable(Consumer<T> action) {
            if (value != null) {
                action.accept(value);
            }
        }

        @Override
        public T getIfUnique() {
            return value;
        }

        @Override
        public T getIfUnique(Supplier<T> defaultSupplier) {
            return value != null ? value : defaultSupplier.get();
        }

        @Override
        public void ifUnique(Consumer<T> action) {
            if (value != null) {
                action.accept(value);
            }
        }

        @Override
        public Stream<T> stream() {
            return value == null ? Stream.empty() : Stream.of(value);
        }

        @Override
        public Iterator<T> iterator() {
            return value == null ? Collections.emptyIterator() : Collections.singleton(value).iterator();
        }

        @Override
        public Spliterator<T> spliterator() {
            return value == null ? Spliterators.emptySpliterator() : Collections.singleton(value).spliterator();
        }
    }

    @Test
    void healthDetails_Unauthorized_WhenNotAdmin() throws Exception {
        mockMvc.perform(get("/api/admin/health-details"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void healthDetails_Forbidden_WhenNotAdminRole() throws Exception {
        mockMvc.perform(get("/api/admin/health-details"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void healthDetails_ReturnsDetails_WhenAdmin() throws Exception {
        when(healthEndpoint.health()).thenReturn(Health.status(Status.UP).build());
        when(infoEndpoint.info()).thenReturn(java.util.Map.of("app", java.util.Map.of("name", "product-service")));

        ObjectProvider<HealthEndpoint> healthProvider = new FixedObjectProvider<>(healthEndpoint);
        ObjectProvider<InfoEndpoint> infoProvider = new FixedObjectProvider<>(infoEndpoint);

        MockMvc standaloneMvc = MockMvcBuilders
                .standaloneSetup(new AdminHealthController(healthProvider, infoProvider))
                .build();

        standaloneMvc.perform(get("/api/admin/health-details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.info.app.name").value("product-service"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void healthDetails_ReturnsUnknown_WhenEndpointsUnavailable() throws Exception {
        ObjectProvider<HealthEndpoint> nullHealthProvider = new FixedObjectProvider<>(null);
        ObjectProvider<InfoEndpoint> nullInfoProvider = new FixedObjectProvider<>(null);

        MockMvc mvc = MockMvcBuilders
                .standaloneSetup(new AdminHealthController(nullHealthProvider, nullInfoProvider))
                .build();

        mvc.perform(get("/api/admin/health-details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UNKNOWN"))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void healthDetails_HealthAvailable_InfoMissing_ShouldReturnUnknown() throws Exception {
        ObjectProvider<HealthEndpoint> healthProvider = new FixedObjectProvider<>(healthEndpoint);
        ObjectProvider<InfoEndpoint> nullInfoProvider = new FixedObjectProvider<>(null);

        MockMvc mvc = MockMvcBuilders
                .standaloneSetup(new AdminHealthController(healthProvider, nullInfoProvider))
                .build();

        mvc.perform(get("/api/admin/health-details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UNKNOWN"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void healthDetails_InfoAvailable_HealthMissing_ShouldReturnUnknown() throws Exception {
        ObjectProvider<HealthEndpoint> nullHealthProvider = new FixedObjectProvider<>(null);
        ObjectProvider<InfoEndpoint> infoProvider = new FixedObjectProvider<>(infoEndpoint);

        MockMvc mvc = MockMvcBuilders
                .standaloneSetup(new AdminHealthController(nullHealthProvider, infoProvider))
                .build();

        mvc.perform(get("/api/admin/health-details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UNKNOWN"));
    }
}