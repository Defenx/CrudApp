package lab.andersen.crudapp.repositories;

import lab.andersen.crudapp.dto.HotelDTO;
import lab.andersen.crudapp.entities.Country;
import lab.andersen.crudapp.entities.Hotel;
import lab.andersen.crudapp.utils.SqlUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HotelRepository {

    private final Session session;

    public HotelRepository(Session session) {
        this.session = session;
    }

    public List<HotelDTO> getHotels() {
        session.beginTransaction();
        List<Hotel> hotels;
        Query query = session.createQuery("FROM Hotel");
        hotels = (List<Hotel>) query.list();
        List<HotelDTO> hotelDTOS = new ArrayList<>();
        for (int i = 0; i < hotels.size(); i++) {
            hotelDTOS.add(new HotelDTO(hotels.get(i).getIdHotel(),hotels.get(i).getName(),hotels.get(i).getCountry()));
        }
        session.getTransaction().commit();
        return hotelDTOS;
    }

    public HotelDTO createHotel(String name, long idCountry) {
        session.beginTransaction();
        Hotel createdHotel = new Hotel();
        createdHotel.setName(name);
        Country country = session.load(Country.class, idCountry);
        createdHotel.setCountry(country);
        session.save(createdHotel);
        session.getTransaction().commit();
        return new HotelDTO(SqlUtil.findIdForCreateOperation(session, "SELECT MAX(idHotel) FROM Hotel"), name, country);
    }

    public long deleteHotelById(long id) {
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Hotel WHERE idHotel = :idForDelete");
        query.setLong("idForDelete", id);
        query.executeUpdate();
        session.getTransaction().commit();
        return id;
    }

    public HotelDTO updateHotel(long id, long idNewCountry, String name) {

        session.beginTransaction();
        Query query = session.createQuery("UPDATE Hotel SET name = :nameForUpdate WHERE idHotel = :idCountryWhichUpdated");
        query.setString("nameForUpdate", name);
        query.setLong("idCountryWhichUpdated", id);
        Country country = session.load(Country.class, idNewCountry);
        query.executeUpdate();
        session.getTransaction().commit();
        return new HotelDTO(id, name, country);
    }

    public Optional<HotelDTO> getHotelById(long id) {
        session.beginTransaction();
        Optional<Hotel> hotel;
        hotel = Optional.of((session.load(Hotel.class, id)));
        Optional<HotelDTO> hotelDTO;
        hotelDTO = Optional.of(new HotelDTO(hotel.get().getIdHotel(),hotel.get().getName(),hotel.get().getCountry()));
        return hotelDTO;
    }
}

