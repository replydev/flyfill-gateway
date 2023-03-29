package org.flyfill;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ping")
public class PingController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "Pong";
    }
}
