package org.flyfill.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class JacksonConfig {

    @Produces
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
