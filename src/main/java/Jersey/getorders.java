package Jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/order")
public class getorders {
    @GET
    @Produces("applicatom/json")
    public String GetOrderInfo(){
        JsonObjectBuilder Builder = new JsonObjectBuilder();
        return "";
    }
}
