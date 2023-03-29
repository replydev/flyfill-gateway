package org.flyfill.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.Session;

import org.flyfill.dto.PasswordFillRequestDTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Store websocket sessions with browser extensions and dispatch password fill
 * requests
 */
@ApplicationScoped
@Slf4j
public class RequestDispatcher {

    @Inject
    ObjectMapper objectMapper;

    /**
     * Map of web socket extensions where
     * 
     * - The key is the random generated id from the browser extension
     * - The value is the Websocket session object
     * 
     */
    Map<String, Session> sessions = new ConcurrentHashMap<>();

    /**
     * Get Websocket session by id
     * 
     * @param id The session id
     * @return The Websocket session object
     */
    public Session getSession(String id) {
        return Optional.ofNullable(sessions.get(id)).orElseThrow();
    }

    public void openSession(@NonNull String id, Session session) {
        sessions.put(id, session);
        log.trace("Session opened", id);
    }

    public void closeSession(@NonNull String id) {
        sessions.remove(id);
        log.trace("Session closed", id);
    }

    public void dispatchRequest(PasswordFillRequestDTO passwordFillRequestDTO) throws JsonProcessingException {
        Session session = getSession(passwordFillRequestDTO.getSessionId());
        session.getAsyncRemote().sendText(objectMapper.writeValueAsString(passwordFillRequestDTO), result -> {
            if (result.getException() != null) {
                log.error("Error during password fill request dispatch", result.getException());
            }
        });
    }
}
