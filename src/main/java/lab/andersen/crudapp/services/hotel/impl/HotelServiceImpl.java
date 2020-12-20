package lab.andersen.crudapp.services.hotel.impl;

import lab.andersen.crudapp.entities.Hotel;
import lab.andersen.crudapp.repositories.HotelRepository;
import lab.andersen.crudapp.services.hotel.HotelService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelRepository.getHotels();
    }

    @Override
    public Hotel createHotel(String name, long idCountry) {
        return hotelRepository.createHotel(name, idCountry);
    }

    @Override
    public long deleteHotelById(long id) {
        return hotelRepository.deleteHotelById(id);
    }

    @Override
    public Hotel updateHotel(long id, long idNewCountry, String name) {
        return hotelRepository.updateHotel(id, idNewCountry, name);
    }

    @Override
    public Optional<Hotel> getHotelById(long id) {
        return hotelRepository.getHotelById(id);
    }
}
