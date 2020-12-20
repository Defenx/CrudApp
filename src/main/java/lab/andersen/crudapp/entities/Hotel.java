package lab.andersen.crudapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "hotel", schema = "touragency")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hotel {

    @Id
    @Column(name = "id_hotel", nullable = false)
    private long idHotel;

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;

}
