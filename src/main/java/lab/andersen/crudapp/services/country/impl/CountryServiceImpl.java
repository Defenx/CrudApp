package lab.andersen.crudapp.services.country.impl;

import lab.andersen.crudapp.dto.CountryDTO;
import lab.andersen.crudapp.repositories.CountryRepository;
import lab.andersen.crudapp.services.country.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public CountryDTO createCountry(String name) {

        return countryRepository.createCountry(name);
    }

    @Override
    public List<CountryDTO> getCountries() {
        return countryRepository.getCountries();
    }

    @Override
    public long deleteCountryById(long id) {
        return countryRepository.deleteCountryById(id);
    }

    @Override
    public CountryDTO updateCountry(long id, String name) {
        return countryRepository.updateCountry(id, name);
    }

    @Override
    public Optional<CountryDTO> getCountryById(long id) {
        return countryRepository.getCountryById(id);
    }
}
