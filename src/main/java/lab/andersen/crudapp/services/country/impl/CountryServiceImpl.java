package lab.andersen.crudapp.services.country.impl;

import lab.andersen.crudapp.entities.Country;
import lab.andersen.crudapp.repositories.CountryRepository;
import lab.andersen.crudapp.services.country.CountryService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void createCountry(String name) throws SQLException {
        countryRepository.createCountry(name);
    }

    @Override
    public List<Country> getCountries() throws SQLException {
        return countryRepository.getCountries();
    }

    @Override
    public void deleteCountryById(int id) throws SQLException {
        countryRepository.deleteCountryById(id);
    }

    @Override
    public int updateCountry(int id, String name) throws SQLException {
        return countryRepository.updateCountry(id, name);
    }

    @Override
    public Optional<Country> getCountryById(int id) throws SQLException {
        return countryRepository.getCountryById(id);
    }
}
