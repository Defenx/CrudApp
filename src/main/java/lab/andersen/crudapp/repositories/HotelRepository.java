package lab.andersen.crudapp.repositories;

import lab.andersen.crudapp.dto.HotelDTO;
import lab.andersen.crudapp.entities.Country;
import lab.andersen.crudapp.entities.Hotel;
import lab.andersen.crudapp.utils.SqlUtil;
import lombok.AllArgsConstructor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class HotelRepository {

    private final SessionFactory sessionFactory;

    public List<HotelDTO> getHotels() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Hotel> hotels;
        Query query = session.createQuery("FROM Hotel");
        hotels = (List<Hotel>) query.list();
        List<HotelDTO> hotelDTOS = new ArrayList<>();
        for (int i = 0; i < hotels.size(); i++) {
            hotelDTOS.add(new HotelDTO(hotels.get(i).getIdHotel(),hotels.get(i).getName(),hotels.get(i).getCountry()));
        }
        session.getTransaction().commit();
        session.close();
        return hotelDTOS;
    }

    public HotelDTO createHotel(String name, long idCountry) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Hotel createdHotel = new Hotel();
        createdHotel.setName(name);
        Country country = session.load(Country.class, idCountry);
        createdHotel.setCountry(country);
        session.save(createdHotel);
        session.getTransaction().commit();
        HotelDTO hotelDTO = new HotelDTO(SqlUtil.findIdForCreateOperation(session, "SELECT MAX(idHotel) FROM Hotel"), name, country);
        session.close();
        return hotelDTO;
    }

    public long deleteHotelById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Hotel WHERE idHotel = :idForDelete");
        query.setLong("idForDelete", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public HotelDTO updateHotel(long id, long idNewCountry, String name) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Hotel SET name = :nameForUpdate WHERE idHotel = :idCountryWhichUpdated");
        query.setString("nameForUpdate", name);
        query.setLong("idCountryWhichUpdated", id);
        Country country = session.load(Country.class, idNewCountry);
        query.executeUpdate();
        session.getTransaction().commit();
        HotelDTO hotelDTO = new HotelDTO(id, name, country);
        session.close();
        return hotelDTO;
    }

    public Optional<HotelDTO> getHotelById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Optional<Hotel> hotel;
        hotel = Optional.of((session.load(Hotel.class, id)));
        Optional<HotelDTO> hotelDTO;
        hotelDTO = Optional.of(new HotelDTO(hotel.get().getIdHotel(),hotel.get().getName(),hotel.get().getCountry()));
        session.close();
        return hotelDTO;
    }
}

