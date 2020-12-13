package lab.andersen.crudapp.services.hotel.impl;

import lab.andersen.crudapp.entities.Hotel;
import lab.andersen.crudapp.repositories.HotelRepository;
import lab.andersen.crudapp.services.hotel.HotelService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    @Override
    public void createHotel(String name, int idCountry) throws SQLException {
        hotelRepository.createCountry(name,idCountry);
    }

    @Override
    public List<Hotel> getHotels() throws SQLException {
        return hotelRepository.getHotels();
    }

    @Override
    public void deleteHotelById(int id) throws SQLException {
        hotelRepository.deleteHotelById(id);
    }

    @Override
    public int updateHotel(int id,int idNewCountry, String name) throws SQLException {
        return hotelRepository.updateHotel(id,idNewCountry,name);
    }

    @Override
    public Optional<Hotel> getHotelById(int id) throws SQLException {
        return hotelRepository.getHotelById(id);
    }
}
