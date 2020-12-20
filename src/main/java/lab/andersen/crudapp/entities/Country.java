package lab.andersen.crudapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "country", schema = "touragency")
@NoArgsConstructor
@Data
public class Country {
    @Id
    @Column(name = "country_id", nullable = false)
    private long countryId;

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public Country(long countryId, String name) {
        this.countryId = countryId;
        this.name = name;
    }
}
