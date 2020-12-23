package lab.andersen.crudapp.repositories;

import lab.andersen.crudapp.dto.CountryDTO;
import lab.andersen.crudapp.entities.Country;
import lab.andersen.crudapp.utils.SqlUtil;
import lombok.AllArgsConstructor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class CountryRepository {
    private final SessionFactory sessionFactory;

    public List<CountryDTO> getCountries() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Country> countries;
        Query query =  session.createQuery("FROM Country");
        countries = (List<Country>) query.list();
        List<CountryDTO> countryDTOS = new ArrayList<>();
        for (int i = 0; i < countries.size(); i++) {
            countryDTOS.add(new CountryDTO(countries.get(i).getCountryId(),countries.get(i).getName()));
        }
        session.getTransaction().commit();
        session.close();
        return countryDTOS;
    }

    public long deleteCountryById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Country WHERE countryId = :idForDelete");
        query.setLong("idForDelete", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public CountryDTO createCountry(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Country createdCountry = new Country();
        createdCountry.setName(name);
        session.save(createdCountry);
        session.getTransaction().commit();
        CountryDTO countryDTO = new CountryDTO(SqlUtil.findIdForCreateOperation(session, "SELECT MAX(countryId) FROM Country"), name);
        session.close();
        return countryDTO;
    }

    public CountryDTO updateCountry(long id, String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Country SET name = :nameForUpdate WHERE countryId = :idCountryWhichUpdated");
        query.setString("nameForUpdate", name);
        query.setLong("idCountryWhichUpdated", id);
        query.executeUpdate();
        session.getTransaction().commit();
        CountryDTO countryDTO = new CountryDTO(id, name);
        session.close();
        return countryDTO;
    }

    public Optional<CountryDTO> getCountryById(long id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Optional<Country> country;
        country = Optional.of((session.load(Country.class, id)));
        Optional<CountryDTO> countryDTO;
        countryDTO = Optional.of(new CountryDTO(country.get().getCountryId(),country.get().getName()));
        session.close();
        return countryDTO;
    }
}
