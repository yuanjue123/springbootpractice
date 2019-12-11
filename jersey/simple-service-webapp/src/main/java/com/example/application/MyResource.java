package com.example.application;

import com.example.param.Person;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public final class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!".concat(Objects.toString(this));
    }

    @GET
    @Path("/getPerson")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson() {
        final Person person = new Person();
        person.setName("carter");
        person.setAge(31);
        return person;
    }
}
