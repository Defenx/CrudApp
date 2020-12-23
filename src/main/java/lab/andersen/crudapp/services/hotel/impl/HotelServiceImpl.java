package lab.andersen.crudapp.services.hotel.impl;

import lab.andersen.crudapp.dto.HotelDTO;
import lab.andersen.crudapp.entities.Hotel;
import lab.andersen.crudapp.repositories.HotelRepository;
import lab.andersen.crudapp.services.hotel.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public List<HotelDTO> getHotels() {
        return hotelRepository.getHotels();
    }

    @Override
    public HotelDTO createHotel(String name, long idCountry) {
        return hotelRepository.createHotel(name, idCountry);
    }

    @Override
    public long deleteHotelById(long id) {
        return hotelRepository.deleteHotelById(id);
    }

    @Override
    public HotelDTO updateHotel(long id, long idNewCountry, String name) {
        return hotelRepository.updateHotel(id, idNewCountry, name);
    }

    @Override
    public Optional<HotelDTO> getHotelById(long id) {
        return hotelRepository.getHotelById(id);
    }
}
