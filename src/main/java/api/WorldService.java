package api;

import java.util.List;

public class WorldService {
    private CountryDao countryDao = new CountryPostgresDaoImpl();

    public List<Country> getAllCountries() {
        return this.countryDao.findAll();
    }
    public Boolean delete(String id) {
        return this.countryDao.delete(id);
    }
    public Boolean update(Country country) {
        return this.countryDao.update(country);
    }
    public Boolean insert(Country country){
        return this.countryDao.insert(country);
    }

}
