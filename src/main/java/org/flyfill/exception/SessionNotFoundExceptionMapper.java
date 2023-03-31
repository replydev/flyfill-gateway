package org.flyfill.exception;


import org.flyfill.dto.ErrorMessageDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SessionNotFoundExceptionMapper implements ExceptionMapper<SessionNotFoundException> {

    @Override
    public Response toResponse(SessionNotFoundException exception) {
        ErrorMessageDTO errorMessageDTO = ErrorMessageDTO
                .builder()
                .message(exception.getMessage())
                .status(Response.Status.NOT_FOUND.getStatusCode())
                .build();
        return Response.status(Response.Status.NOT_FOUND).entity(errorMessageDTO).build();
    }
}
