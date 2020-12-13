package lab.andersen.crudapp;

import lab.andersen.crudapp.exceptions.IdNotFoundException;
import lab.andersen.crudapp.repositories.CountryRepository;
import lab.andersen.crudapp.repositories.HotelRepository;
import lab.andersen.crudapp.services.country.CountryService;
import lab.andersen.crudapp.services.country.impl.CountryServiceImpl;
import lab.andersen.crudapp.services.hotel.HotelService;
import lab.andersen.crudapp.services.hotel.impl.HotelServiceImpl;
import lab.andersen.crudapp.utils.DatabasePoolConnection;
import lab.andersen.crudapp.utils.MenuUtil;

import java.sql.Connection;
import java.util.Scanner;
import java.util.function.Supplier;

public class Runner {
    public static void main(String[] args) throws Throwable {

        DatabasePoolConnection databasePoolConnection = new DatabasePoolConnection();
        Connection connection = databasePoolConnection.getConnection();
        CountryRepository repository = new CountryRepository(connection);
        CountryService countryService = new CountryServiceImpl(repository);
        HotelRepository hotelRepository = new HotelRepository(connection);
        HotelService hotelService = new HotelServiceImpl(hotelRepository);
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            MenuUtil.printMenu();
            int menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    MenuUtil.printSubmenu();
                    int submenu = scanner.nextInt();
                    switch (submenu) {
                        case 1:
                            System.out.println("input name: ");
                            String countryName = scanner.next();
                            countryService.createCountry(countryName);
                            break;
                        case 2:
                            System.out.println(countryService.getCountries());
                            break;
                        case 3:
                            System.out.println("input id:");
                            int id = scanner.nextInt();
                            System.out.println(countryService.getCountryById(id).orElseThrow((Supplier<Throwable>) () -> new IdNotFoundException("id " + id + " not found")));
                            break;
                        case 4:
                            System.out.println("input id");
                            int idToUpdate = scanner.nextInt();
                            System.out.println("input new name");
                            String nameToUpdate = scanner.next();
                            countryService.updateCountry(idToUpdate, nameToUpdate);
                            break;
                        case 5:
                            System.out.println("input id for delete");
                            int idForDelete = scanner.nextInt();
                            countryService.deleteCountryById(idForDelete);
                            break;
                    }
                    break;
                case 2:
                    MenuUtil.printSubmenu();
                    int submenuTwo = scanner.nextInt();
                    switch (submenuTwo) {
                        case 1:
                            System.out.println("input name: ");
                            String hotelName = scanner.next();
                            System.out.println("input id country where hotel located");
                            int idCountry = scanner.nextInt();
                            hotelService.createHotel(hotelName, idCountry);
                            break;
                        case 2:
                            System.out.println(hotelService.getHotels());
                            break;
                        case 3:
                            System.out.println("input id:");
                            int id = scanner.nextInt();
                            System.out.println(hotelService.getHotelById(id).orElseThrow((Supplier<Throwable>) () -> new IdNotFoundException("id " + id +" not found")));
                            break;
                        case 4:
                            System.out.println("input id");
                            int idToUpdate = scanner.nextInt();
                            System.out.println("input new name");
                            String nameToUpdate = scanner.next();
                            System.out.println("input id new country");
                            int idNewCountry = scanner.nextInt();
                            hotelService.updateHotel(idToUpdate,idNewCountry, nameToUpdate);
                            break;
                        case 5:
                            System.out.println("input id for delete");
                            int idForDelete = scanner.nextInt();
                            hotelService.deleteHotelById(idForDelete);
                            break;
                    }
                    break;
                default:
                    isRunning = false;
            }
        }
        connection.close();
    }
}

