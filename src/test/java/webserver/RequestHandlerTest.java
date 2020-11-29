package webserver;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestHandlerTest {
    @Test
    void handleUserCreate() {
        HttpRequest httpRequest = new HttpRequest("POST", "/usr/create", "HTTP 1.1");
        httpRequest.setEntity(ImmutableMap.of("userId", "red"));
        assertThat(RequestHandler.handleUserCreate(httpRequest).getLocation())
                .isEqualTo("/index.html");
    }

    @Test
    void handleLoginSuccess() {
        HttpRequest httpRequest = new HttpRequest("POST", "/user/login", "HTTP 1.1");
        httpRequest.setEntity(ImmutableMap.of("userId", "blue", "password", "1234"));
        assertThat(RequestHandler.handleLogin(httpRequest).getHeaders())
                .containsExactly("Set-Cookie: logined=true; Path=/", "Location: /index.html");
    }
}
