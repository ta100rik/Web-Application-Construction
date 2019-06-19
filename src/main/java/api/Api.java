package api;

import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/test")
public class Api {
    private WorldService worldService = ServiceProvider.getWorldService();

    @GET
    @Produces("application/json")
    public String GetWorld(){
        List<Country> lijst = worldService.getAllCountries();

        JsonArrayBuilder builder = Json.createArrayBuilder();
        for(Country cs : lijst){
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("Code",cs.getCode());
            objectBuilder.add("Name",cs.getName());
            objectBuilder.add("Continent",cs.getContinent());
            objectBuilder.add("Surface",cs.getSurface());
            objectBuilder.add("Population",cs.getPopulation());
            objectBuilder.add("Region",cs.getRegion());
            objectBuilder.add("Capital",cs.getCapital());
            objectBuilder.add("Latitude",cs.getLatitude());
            objectBuilder.add("Longtitude",cs.getLongtitude());
            builder.add(objectBuilder);
        }
        JsonArray list = builder.build();

        return list.toString();

    }
    @DELETE
    @Path("{id}")
    @Produces("application/json")

    public String deleteOrderById(@PathParam("id") String id) {
        worldService.delete(id);
        return "ok";
    }
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String Update(@FormParam ("code") String code,@FormParam ("landval") String landval,@FormParam ("Hoofdstad") String Hoofdstad,@FormParam ("Regio") String Regio,@FormParam ("Oppervlakte") String Oppervlakte,
                         @FormParam ("Inwoners") String Inwoners){
        Country updateCountry = new Country(code,landval,"",Regio,Float.parseFloat(Oppervlakte),Integer.parseInt(Inwoners),"",Hoofdstad,"","");
        worldService.update(updateCountry);
        return "ok";
    }
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String insert(@FormParam ("code") String code,@FormParam ("landval") String landval,@FormParam ("Hoofdstad") String Hoofdstad,@FormParam ("Regio") String Regio,@FormParam ("Oppervlakte") String Oppervlakte,
                         @FormParam ("Inwoners") String Inwoners){
        Country updateCountry = new Country(code,landval,"",Regio,Float.parseFloat(Oppervlakte),Integer.parseInt(Inwoners),"",Hoofdstad,"","");
        System.out.println(updateCountry.getCapital());
        System.out.println(worldService.insert(updateCountry));
        return "ok";
    }
}
