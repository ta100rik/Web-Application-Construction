package Login;

import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Key;

@Path("/login")
public class Main {
    final static public Key key = MacProvider.generateKey();

    private ServiceProvider SP = new ServiceProvider();
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("user") String user, @FormParam ("password") String password){
        final String RESULT = SP.login(user,password,key);
        if(RESULT != null && !RESULT.isEmpty()){
            return Response.ok(RESULT).build();
        }else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }
    @GET
    @Path("{listname}")
    @RolesAllowed("user")
    @Produces("application/json")
    public String getMyPlaylist(@Context SecurityContext sc,
                                @PathParam("listname") String playlistName) {
        return playlistName;
    }

}
