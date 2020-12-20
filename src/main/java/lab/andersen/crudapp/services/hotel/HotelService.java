package lab.andersen.crudapp.services.hotel;

import lab.andersen.crudapp.entities.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    List<Hotel> getHotels();

    Hotel createHotel(String name, long idCountry);

    long deleteHotelById(long id);

    Hotel updateHotel(long id, long idNewCountry, String name);

    Optional<Hotel> getHotelById(long id);
}
