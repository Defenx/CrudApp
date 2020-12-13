package lab.andersen.crudapp.services.hotel;

import lab.andersen.crudapp.entities.Hotel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface HotelService {
    void createHotel(String name, int idCountry) throws SQLException;
    List<Hotel> getHotels() throws SQLException;
    void deleteHotelById(int id) throws SQLException;
    int updateHotel(int id, int idNewCountry, String name) throws SQLException;
    Optional<Hotel> getHotelById(int id) throws SQLException;
}
