package lab.andersen.crudapp.dto;

import lab.andersen.crudapp.entities.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    private long idHotel;
    private String name;
    private Country country;
}
