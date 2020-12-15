package lab.andersen.crudapp.repositories;

import lab.andersen.crudapp.entities.Country;

import lab.andersen.crudapp.utils.SqlUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryRepository {
    private final Connection connection;

    private static final Logger log = Logger.getLogger(CountryRepository.class);

    public CountryRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Country> getCountries() throws SQLException {
        log.info("method getCountries started");
        Statement statement;
        ResultSet resultSet;
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from country");
        List<Country> countries = new ArrayList<>();
        while (resultSet.next()) {
            countries.add(new Country(resultSet.getLong("country_id"), resultSet.getString("name")));
        }
        resultSet.close();
        statement.close();
        log.info(countries);
        return countries;

    }

    public long deleteCountryById(int id) throws SQLException {
        log.info("method deleteCountryById started");
        PreparedStatement statement;
        statement = connection.prepareStatement("delete from country where country_id = ?");
        statement.setInt(1, id);
        statement.execute();
        statement.close();
        log.info("country by " + id);
        return id;
    }

    public Country createCountry(String name) throws SQLException {
        log.info("method createCountry started");
        PreparedStatement statement;

        statement = connection.prepareStatement("insert into country(name) values (?)");
        statement.setString(1, name);
        statement.execute();
        statement.close();
        log.info("country with name" + name + "was created");

        return new Country(SqlUtil.findIdForCreateOperation(connection, "select MAX(country_id) from country"), name);

    }

    public Country updateCountry(int id, String name) throws SQLException {
        log.info("method updateCountry started");
        PreparedStatement statement;
        statement = connection.prepareStatement("update country set name = ? where country_id = ?");
        statement.setString(1, name);
        statement.setInt(2, id);
        statement.execute();
        Country updatedCountry = new Country(id, name);
        statement.close();
        log.info("country updated");
        return updatedCountry;
    }

    public Optional<Country> getCountryById(int id) throws SQLException {
        log.info("method getCountryById started");
        PreparedStatement statement;
        statement = connection.prepareStatement("select * from country where country_id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Optional<Country> country = Optional.empty();
        if (resultSet.next()) {
            country = Optional.of(new Country(resultSet.getInt("country_id"), resultSet.getString("name")));
        }
        resultSet.close();
        statement.close();
        log.info(country);
        return country;
    }
}
