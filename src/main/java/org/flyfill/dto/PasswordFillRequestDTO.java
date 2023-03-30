package org.flyfill.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Request from the client to be sent to the subscribed browser extension
 * which contains encrypted username and password, with the extension public
 * key.
 * <p>
 * The gateway should know nothing about the data in transit.
 */
@Value
@Builder
@Jacksonized
public class PasswordFillRequestDTO {

    /**
     * Session id
     */
    String sessionId;

    /**
     * Username encrypted with browser extension public key
     */
    String encryptedUsername;

    /**
     * Password encrypted with browser extension public key
     */
    String encryptedPassword;
}
