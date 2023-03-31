package org.flyfill.dto;


import lombok.Builder;
import lombok.Value;

/**
 * A simple error message POJO
 */
@Value
@Builder
public class ErrorMessageDTO {

    String message;
    int status;
}
