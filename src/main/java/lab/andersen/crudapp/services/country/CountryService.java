package lab.andersen.crudapp.services.country;

import lab.andersen.crudapp.entities.Country;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    Country createCountry(String name);
    List<Country> getCountries();
    long deleteCountryById(long id);
    Country updateCountry(long id, String name);
    Optional<Country> getCountryById(long id);
}
