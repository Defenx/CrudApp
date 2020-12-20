package lab.andersen.crudapp.services.hotel;

import lab.andersen.crudapp.dto.HotelDTO;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    List<HotelDTO> getHotels();

    HotelDTO createHotel(String name, long idCountry);

    long deleteHotelById(long id);

    HotelDTO updateHotel(long id, long idNewCountry, String name);

    Optional<HotelDTO> getHotelById(long id);

}
