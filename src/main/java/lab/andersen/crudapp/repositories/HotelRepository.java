package lab.andersen.crudapp.repositories;

import lab.andersen.crudapp.entities.Hotel;
import lab.andersen.crudapp.utils.SqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HotelRepository {

    private final Connection connection;

    public HotelRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Hotel> getHotels() throws SQLException {
        Statement statement;
        ResultSet resultSet;
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select hotel.id_hotel, hotel.name, country.name from hotel inner join country on hotel.country_id = country.country_id");
        List<Hotel> hotels = new ArrayList<>();
        while (resultSet.next()) {
            hotels.add(new Hotel(resultSet.getInt("id_hotel"), resultSet.getString("name"), resultSet.getString("country.name")));
        }
        resultSet.close();
        statement.close();
        return hotels;
    }

    public Hotel createHotel(String name, int idCountry) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("insert into hotel(name, country_id) values (?,?)");
        statement.setString(1, name);
        statement.setInt(2, idCountry);
        statement.execute();
        statement.close();
        return new Hotel(SqlUtil.findIdForCreateOperation(connection, "select MAX(id_hotel) from hotel"), name, SqlUtil.findNameByIdCountry(connection, "select country.name from country where country.country_id = ?", idCountry));
    }

    public long deleteHotelById(int id) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("delete from hotel where id_hotel = ?");
        statement.setInt(1, id);
        statement.execute();
        statement.close();
        return id;
    }

    public Hotel updateHotel(int id, int idNewCountry, String name) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("update hotel set name = ?,country_id = ? where id_hotel = ?");
        statement.setString(1, name);
        statement.setInt(2, idNewCountry);
        statement.setInt(3, id);
        statement.execute();
        statement.close();

        return new Hotel(id, name, SqlUtil.findNameByIdCountry(connection, "select name from country where country_id = ?", id));
    }

    public Optional<Hotel> getHotelById(int id) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("select hotel.id_hotel, hotel.name, country.name from hotel inner join country on hotel.country_id = country.country_id where id_hotel = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Optional<Hotel> hotel = Optional.empty();
        if (resultSet.next()) {
            hotel = Optional.of(new Hotel(resultSet.getInt("id_hotel"), resultSet.getString("name"), resultSet.getString("country.name")));

        }
        resultSet.close();
        statement.close();
        return hotel;
    }

}
