package lab.andersen.crudapp.services.country;

import lab.andersen.crudapp.entities.Country;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    void createCountry(String name) throws SQLException;
    List<Country> getCountries() throws SQLException;
    void deleteCountryById(int id) throws SQLException;
    int updateCountry(int id, String name) throws SQLException;
    Optional<Country> getCountryById(int id) throws SQLException;
}
