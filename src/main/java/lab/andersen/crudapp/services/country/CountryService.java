package lab.andersen.crudapp.services.country;

import lab.andersen.crudapp.dto.CountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    CountryDTO createCountry(String name);
    List<CountryDTO> getCountries();
    long deleteCountryById(long id);
    CountryDTO updateCountry(long id, String name);
    Optional<CountryDTO> getCountryById(long id);
}
