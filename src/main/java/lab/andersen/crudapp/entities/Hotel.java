package lab.andersen.crudapp.entities;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Hotel {
    private long id;
    private String name;
    private String countryName;
    //private Country country;
}
