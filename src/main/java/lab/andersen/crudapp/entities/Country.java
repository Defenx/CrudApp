package lab.andersen.crudapp.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Country {
     private long id;
     private String name;
    //private List<Hotel> hotels;
}
