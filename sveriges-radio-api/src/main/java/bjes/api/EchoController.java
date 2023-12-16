package bjes.api;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

@Controller
@Path("echo")
public class EchoController {

    @GET
    @Path("{text}")
    public Response echo (@PathParam("text") String text) {
        return Response.ok(text).build();
    }
}
