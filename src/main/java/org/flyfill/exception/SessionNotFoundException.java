package org.flyfill.exception;

public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException(String sessionId) {
        super("Session id not found: " + sessionId);
    }
}
