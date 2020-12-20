package lab.andersen.crudapp.services.country.impl;

import lab.andersen.crudapp.entities.Country;
import lab.andersen.crudapp.repositories.CountryRepository;
import lab.andersen.crudapp.services.country.CountryService;

import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country createCountry(String name) {

        return countryRepository.createCountry(name);
    }

    @Override
    public List<Country> getCountries() {
        return countryRepository.getCountries();
    }

    @Override
    public long deleteCountryById(long id) {
        return countryRepository.deleteCountryById(id);
    }

    @Override
    public Country updateCountry(long id, String name) {
        return countryRepository.updateCountry(id, name);
    }

    @Override
    public Optional<Country> getCountryById(long id) {
        return countryRepository.getCountryById(id);
    }
}
