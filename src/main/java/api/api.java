package api;

import Jersey.WorldService;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/test")
public class api {
    private WorldService ws  = new WorldService();
    @GET
    @Produces("application/json")
    public String GetWorld(){
        return "ok";
    }

}
