package lab.andersen.crudapp;

import lab.andersen.crudapp.services.country.CountryService;
import lab.andersen.crudapp.services.hotel.HotelService;
import lab.andersen.crudapp.utils.MenuUtil;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SessionFactory sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
        CountryService countryService = context.getBean("countryService" ,CountryService.class);
        HotelService hotelService = context.getBean("hotelService" ,HotelService.class);

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
                            System.out.println(countryService.getCountryById(id));
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
                            System.out.println(hotelService.getHotelById(id));
                            break;
                        case 4:
                            System.out.println("input id");
                            int idToUpdate = scanner.nextInt();
                            System.out.println("input new name");
                            String nameToUpdate = scanner.next();
                            System.out.println("input id new country");
                            int idNewCountry = scanner.nextInt();
                            hotelService.updateHotel(idToUpdate, idNewCountry, nameToUpdate);
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
        sessionFactory.close();
        scanner.close();
    }
}

