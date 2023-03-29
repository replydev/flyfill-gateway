package org.flyfill.graphql;

import lombok.Builder;
import lombok.Value;

/**
 * Request from the client to be sent to the subscribed browser extension
 * which contains encrypted username and password, with the extension public
 * key.
 * 
 * The gateway should know nothing about the data in transit.
 */
@Value
@Builder
public class PasswordFillRequest {

    /**
     * Username encrypted with browser extension public key
     */
    String encryptedUsername;

    /**
     * Password encrypted with browser extension public key
     */
    String encryptedPassword;
}
