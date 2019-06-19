package api;

import java.util.List;

public interface CountryDao {
    List<Country> findAll();
    boolean delete(String id);
    Boolean update(Country country);
    boolean insert(Country country);
}
