package pl.miq3l.klgjava.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="tenant_id", nullable=false)
    private Person tenant;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="landlord_id", nullable=false)
    private Person landlord;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="place_id", nullable=false)
    private Place place;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateFrom;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateTo;
    private Double price;
}
