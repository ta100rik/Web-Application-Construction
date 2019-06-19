package api;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryPostgresDaoImpl extends PostgresBaseDao implements CountryDao {

    public List<Country> findAll(){
        List<Country> allCountries = new ArrayList<Country>();
        Connection con = super.getConnection();
        try {
        Statement statement = con.createStatement();
        ResultSet resultSet = null;
        resultSet = statement.executeQuery("select * from country order by code");
        while (resultSet.next()) {
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String continent = resultSet.getString("continent");
                String region = resultSet.getString("region");
                String capital = resultSet.getString("capital");
                Float surface = resultSet.getFloat("surfacearea");
                Integer population = resultSet.getInt("population");
                String governmentForm = resultSet.getString("governmentform");

                String Longtitude = resultSet.getString("longitude");

                String Latitude = resultSet.getString("latitude");
                Country new_country = new Country(code,name,continent,region,surface,population,governmentForm,capital,Longtitude,Latitude);
                allCountries.add(new_country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allCountries;
    }

    public boolean delete(String id){
        Connection con = super.getConnection();
        try {


            PreparedStatement st = con.prepareStatement("DELETE FROM country WHERE code = ?");
            st.setString(1,id);
            int result = st.executeUpdate();
            System.out.println(result);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(Country country){
        Connection con = super.getConnection();
        try {
            PreparedStatement st = con.prepareStatement("UPDATE country " +
                    " SET name=?, continent=?, region=?, surfacearea=?, population=?, capital=? " +
                    " WHERE code = ?");
            System.out.println(country.getCapital());
            st.setString(1,country.getCode());
            st.setString(1,country.getName());
            st.setString(2,country.getContinent());
            st.setString(3,country.getRegion());
            st.setFloat(4,country.getSurface());
            st.setInt(5,country.getPopulation());
            st.setString(6,country.getCapital());
            st.setString(7,country.getCode());
            int result = st.executeUpdate();
            System.out.println(result);
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insert(Country country){
        Connection con = super.getConnection();
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO country(code,continent,region,surfacearea,population,name,capital,indepyear,latitude,longitude) VALUES (?, ?, ?, ?, ?, ?,?,?,?,?)");
            st.setString(1,country.getName());
            st.setString(2,country.getContinent());
            st.setString(3,country.getRegion());
            st.setFloat(4,country.getSurface());
            st.setInt(5,country.getPopulation());
            st.setString(6,country.getCapital());
            st.setString(7,country.getCapital());
            st.setInt(8,1);
            st.setInt(9,1);
            st.setInt(10,1);
            int result = st.executeUpdate();
            System.out.println(result);
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
