package pl.miq3l.klgjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double unitPrice;
    private Double placeArea;
    private String description;

    public Place(String name, Double unitPrice, Double placeArea, String description) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.placeArea = placeArea;
        this.description = description;
    }

}
