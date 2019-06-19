package api;

public class Country {
    private String code;
    private String name;
    private String continent;
    private String region;
    private Float surface;
    private Integer population;
    private String governmentForm;
    private String capital;
    private String Longtitude;
    private String Latitude;

    public Country(String code, String name, String continent, String region, Float surface, Integer population, String governmentForm, String capital, String Longtitude, String Latitude) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.surface = surface;
        this.population = population;
        this.governmentForm = governmentForm;
        this.capital = capital;
        this.Longtitude = Longtitude;
        this.Latitude = Latitude;

    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongtitude() {
        return Longtitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public void setLongtitude(String longtitude) {
        Longtitude = longtitude;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Float getSurface() {
        return surface;
    }

    public void setSurface(Float surface) {
        this.surface = surface;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

}
