package org.flyfill.controller;

import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.flyfill.dto.PasswordFillRequestDTO;
import org.flyfill.service.RequestDispatcher;

import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/fill")
public class FillRequestController {

    @Inject
    RequestDispatcher requestDispatcher;

    @POST
    @Path("/queue")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consumeFillRequest(PasswordFillRequestDTO passwordFillRequestDTO) throws JsonProcessingException {
        requestDispatcher.dispatchRequest(passwordFillRequestDTO);
        return Response.ok().build();
    }

}
