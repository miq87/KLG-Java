package pl.miq3l.klgjava.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinColumn(name="tenant_id", nullable=false)
    private Person tenant;
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinColumn(name="landlord_id", nullable=false)
    private Person landlord;
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinColumn(name="place_id", nullable=false)
    private Place place;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateFrom;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateTo;
    private Double price;

    public Reservation(Person tenant, Person landlord, Place place, Date dateFrom, Date dateTo, Double price) {
        this.tenant = tenant;
        this.landlord = landlord;
        this.place = place;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.price = price;
    }
}
