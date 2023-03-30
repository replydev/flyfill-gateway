package org.flyfill.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.flyfill.dto.PasswordFillRequestDTO;
import org.junit.jupiter.api.Test;

import javax.websocket.*;
import java.net.URI;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
class SessionHandlerTest {

    private static final LinkedBlockingDeque<String> MESSAGES = new LinkedBlockingDeque<>();

    @TestHTTPResource("/ws/queue/1234")
    URI uri;

    @Test
    public void testPasswordFill() throws Exception {

        PasswordFillRequestDTO payload = PasswordFillRequestDTO.builder().sessionId("1234").encryptedUsername("test").encryptedPassword("test").build();

        try (Session session = ContainerProvider.getWebSocketContainer().connectToServer(Client.class, uri)) {
            assertEquals("CONNECT", MESSAGES.poll(10, TimeUnit.SECONDS));

            // Send POST request from the android device
            given()
                    .when()
                    .contentType(ContentType.JSON)
                    .body(payload)
                    .post("/fill/queue")
                    .then()
                    .statusCode(200)
                    .body(emptyString());

            String responseFromRequestDispatcher = MESSAGES.poll(10, TimeUnit.SECONDS);

            PasswordFillRequestDTO obtainedPayload = new ObjectMapper().readValue(responseFromRequestDispatcher, PasswordFillRequestDTO.class);

            assertEquals(obtainedPayload, payload);
        }
    }


    // This client endpoint represents the browser extension
    @ClientEndpoint
    public static class Client {

        @OnOpen
        public void open(Session session) {
            MESSAGES.add("CONNECT");
        }

        @OnMessage
        void message(String msg) {
            MESSAGES.add(msg);
        }
    }
}