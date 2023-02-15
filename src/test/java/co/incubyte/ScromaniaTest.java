package co.incubyte;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@MicronautTest
class ScromaniaTest {

    @Inject
    EmbeddedApplication<?> application;
    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }
    @Inject
    @Client("/year")
    HttpClient client;

    @Test
    @DisplayName("should return leap if year divisible by 400")
    public void should_return_yes_if_year_divisible_by_400() {
        Year year = client.toBlocking().retrieve("/4000", Year.class);
        assertThat(year.isLeap()).isTrue();
    }
}
