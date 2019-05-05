package Jersey;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/countries")
public class WorldResource {
    private WorldService ws  = new WorldService();
    private List<Country> country;
    @GET
    @Produces("application/json")
    public String GetWorld(){
        List<Country> country =  ws.getAllCountries();
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for(Country c : country){
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("Code",c.getCode());
            objectBuilder.add("Name",c.getName());
            objectBuilder.add("Capital",c.getCapital());
            objectBuilder.add("Population",c.getPopulation());
            objectBuilder.add("Surface",c.getSurface());
            objectBuilder.add("Continent",c.getContinent());
            objectBuilder.add("Government",c.getGovernment());
            objectBuilder.add("Iso3",c.getIso3());
            objectBuilder.add("Latitude",c.getLatitude());
            objectBuilder.add("Longtitude",c.getLongitude());
            objectBuilder.add("Region",c.getRegion());
            builder.add(objectBuilder);
        }
        JsonArray list = builder.build();

        return list.toString();
    }
    @GET
    @Path("{countrycode}")
    @Produces("application/json")
    public String getByCode(@PathParam("countrycode") String code){
        boolean filterbol = false;
        if(code.equals("largestsurfaces")){
            country =  ws.get10LargestSurfaces();
        }else if(code.equals("largestpopulations")){
            country = ws.get10LargestPopulations();
        }else{
            filterbol = true;
            country =  ws.getAllCountries();
        }

        JsonArrayBuilder builder = Json.createArrayBuilder();
        for(Country c : country){
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            if(filterbol){
                if(c.getCode().toLowerCase().equals(code.toLowerCase()) ){
                    objectBuilder.add("Code",c.getCode());
                    objectBuilder.add("Name",c.getName());
                    objectBuilder.add("Capital",c.getCapital());
                    objectBuilder.add("Population",c.getPopulation());
                    objectBuilder.add("Surface",c.getSurface());
                    objectBuilder.add("Continent",c.getContinent());
                    objectBuilder.add("Government",c.getGovernment());
                    objectBuilder.add("Iso3",c.getIso3());
                    objectBuilder.add("Latitude",c.getLatitude());
                    objectBuilder.add("Longtitude",c.getLongitude());
                    objectBuilder.add("Region",c.getRegion());
                    builder.add(objectBuilder);
                }
            }else{
                objectBuilder.add("Code",c.getCode());
                objectBuilder.add("Name",c.getName());

                objectBuilder.add("Capital",c.getCapital());


                objectBuilder.add("Population",c.getPopulation());
                objectBuilder.add("Surface",c.getSurface());
                objectBuilder.add("Continent",c.getContinent());
                objectBuilder.add("Government",c.getGovernment());
                objectBuilder.add("Iso3",c.getIso3());
                objectBuilder.add("Latitude",c.getLatitude());
                objectBuilder.add("Longtitude",c.getLongitude());
                objectBuilder.add("Region",c.getRegion());
                builder.add(objectBuilder);
            }
        }
        JsonArray list = builder.build();

        return list.toString();

    }
}
