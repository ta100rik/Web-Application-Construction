package Jersey;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/countries")
public class WorldResource {

    @GET
    @Produces("application/json")
    public String GetOrderInfo(){
        JsonObjectBuilder builder = Json.createObjectBuilder();

        return "ok";
    }
}
