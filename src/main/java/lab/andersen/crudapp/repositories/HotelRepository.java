package lab.andersen.crudapp.repositories;

import lab.andersen.crudapp.entities.Hotel;

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
            hotels.add(new Hotel(resultSet.getInt("id_hotel"),resultSet.getString("name"),resultSet.getString("country.name")));
        }
        resultSet.close();
        statement.close();
        return hotels;
    }

    public void createCountry(String name, int idCountry) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("insert into hotel(name, country_id) values (?,?)");
        statement.setString(1, name);
        statement.setInt(2, idCountry);
        statement.execute();
        statement.close();
    }

    public void deleteHotelById(int id) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("delete from hotel where id_hotel = ?");
        statement.setInt(1, id);
        statement.execute();
        statement.close();
    }

    public int updateHotel(int id, int idNewCountry, String name) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("update hotel set name = ?,country_id = ? where id_hotel = ?");
        statement.setString(1, name);
        statement.setInt(2,idNewCountry);
        statement.setInt(3, id);
        int updated = statement.executeUpdate();
        statement.close();
        return updated;
    }

    public Optional<Hotel> getHotelById(int id) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("select hotel.id_hotel, hotel.name, country.name from hotel inner join country on hotel.country_id = country.country_id where id_hotel = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Optional<Hotel> hotel = Optional.empty();
        if (resultSet.next()) {
            hotel = Optional.of(new Hotel(resultSet.getInt("id_hotel"),resultSet.getString("name"),resultSet.getString("country.name")));

        }
        resultSet.close();
        statement.close();
        return hotel;
    }

}
