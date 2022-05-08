package pl.miq3l.klgjava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import pl.miq3l.klgjava.model.Person;
import pl.miq3l.klgjava.model.Reservation;
import pl.miq3l.klgjava.repo.ReservationRepo;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepo reservationRepo;

    @Autowired
    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    public List<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    public Reservation save(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    public Reservation put(Long id, Reservation updated) {
        Optional<Reservation> reservation = reservationRepo.findById(id);
        if(reservation.isPresent()) {
            updated.setId(id);
            reservationRepo.save(updated);
        }
        return null;
    }

    public Reservation patch(Long id, Map<String, Object> fields) {
        Optional<Reservation> reservation = reservationRepo.findById(id);
        ObjectMapper mapper = new ObjectMapper();
        if(reservation.isPresent()) {
            Reservation updated = reservation.get();
            fields.forEach((k, v) -> {
                switch(k) {
                    case "price":
                        updated.setPrice((Double)v);
                        break;
                    case "tenant":
                        updated.setTenant(mapper.convertValue(v, Person.class));
                        break;
                    case "landlord":
                        updated.setTenant(mapper.convertValue(v, Person.class));
                        break;
                    case "dateFrom":
                        try {
                            updated.setDateFrom(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                    .parse((String)v));
                        } catch (ParseException e) {
                            System.out.println("Wrong date format");
                        }
                        break;
                    case "dateTo":
                        try {
                            updated.setDateTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                    .parse((String)v));
                        } catch (ParseException e) {
                            System.out.println("Wrong date format");
                        }
                        break;
                }
            });
            return save(updated);
        }
        return null;
    }

    public List<Reservation> getReservationsByTenantName(String name) {
        return reservationRepo.getReservationsByTenantName(name);
    }

    public List<Reservation> getReservationsByLandlordName(String name) {
        return reservationRepo.getReservationsByLandlordName(name);
    }

    public List<Reservation> getReservationsByPlaceName(String name) {
        return reservationRepo.getReservationsByPlaceName(name);
    }

    public Reservation findById(Long id) {
        return reservationRepo.findById(id).orElse(null);
    }

}
