package com.example.application;

import com.example.param.Person;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
@Log4j2
public final class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    public String getIt() {
        return "Got it!".concat(Objects.toString(this));
    }

    @GET
    @Path("/getPerson")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(Person person) {

        System.out.println("xxx");

        person.setName("carter");
        person.setAge(31);
        return person;
    }

    @GET
    @Path("ex")
    public Object ex() {
        return 1/0;
    }

    @GET
    @Path("person")
    public Person person() {
        return Person.builder().name("aa").age(190).build();
    }

}
