package resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dto.DataCounterDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.JerseyService;

import java.io.IOException;
import java.util.Arrays;

@Path("/counter")
public class CounterResource {
    private static final String HEADER_SUBTRACTION_COUNTER = "Subtraction-Value";
    private static final String COOKIE_AUTH = "hh-auth";
    private final JerseyService service = JerseyService.SERVICE;
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCounter() throws JsonProcessingException {
        DataCounterDto data = service.getDataCounter();
        String dataJson = mapper.writeValueAsString(data);
        return Response.ok(dataJson).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response incrementCounter() throws IOException {
        service.incrementCount();
        return Response.ok("Counter is incremented").build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response subtractionCount(@Context HttpServletRequest req) throws IOException {
        int subtractionValue = Integer.parseInt(req.getHeader(HEADER_SUBTRACTION_COUNTER));
        int factSubtractionValue = service.subtractionCount(subtractionValue);
        String responseStr = String.format("Counter is reduced by %d", factSubtractionValue);
        return Response.ok(responseStr).build();
    }

    @GET
    @Path("/clear")
    @Produces(MediaType.TEXT_PLAIN)
    public Response clearCounter(@Context HttpServletRequest req) {
        if (req.getCookies() != null) {
            Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals(COOKIE_AUTH) && cookie.getValue().length() > 10)
                    .findFirst()
                    .ifPresent(cookie -> {
                        service.clearCounter();
                    });
            return Response.ok("Count cleared").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
