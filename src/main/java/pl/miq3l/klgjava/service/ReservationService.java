package pl.miq3l.klgjava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.miq3l.klgjava.model.Person;
import pl.miq3l.klgjava.model.Place;
import pl.miq3l.klgjava.model.Reservation;
import pl.miq3l.klgjava.repo.ReservationRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ReservationService {
    private final ReservationRepo reservationRepo;
    private final ObjectMapper mapper = new ObjectMapper();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    public List<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    public Reservation save(Reservation newRes) {
        boolean canBook = true;
        List<Reservation> reservations =
                reservationRepo.getReservationsByPlaceName(newRes.getPlace().getName());

        long days = getDateDiff(newRes.getDateFrom(), newRes.getDateTo(), TimeUnit.DAYS);

        newRes.setPrice(newRes.getPlace().getUnitPrice() * days);

        for (Reservation oldRes : reservations) {
            if(oldRes.getId().equals(newRes.getId())) continue;
            if(isReservationBetween(oldRes, newRes)) {
                canBook = false;
            }
        }
        if(canBook) {
            return reservationRepo.save(newRes);
        }
        return null;
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

        if(reservation.isPresent()) {
            Reservation updated = reservation.get();
            fields.forEach((k, v) -> {
                switch(k) {
                    case "price":
                        updated.setPrice((Double)v);
                        break;
                    case "place":
                        updated.setPlace(mapper.convertValue(v, Place.class));
                        break;
                    case "tenant":
                        updated.setTenant(mapper.convertValue(v, Person.class));
                        break;
                    case "landlord":
                        updated.setLandlord(mapper.convertValue(v, Person.class));
                        break;
                    case "dateFrom":
                        try {
                            updated.setDateFrom(dateFormat.parse((String) v));
                        } catch (ParseException e) {
                            System.out.println("Wrong date format");
                        }
                        break;
                    case "dateTo":
                        try {
                            updated.setDateTo(dateFormat.parse((String) v));
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

    public void deleteById(Long id) {
        reservationRepo.deleteById(id);
    }

    private boolean isReservationBetween(Reservation old, Reservation newRes) {
        return newRes.getDateFrom().compareTo(old.getDateFrom()) >= 0
                && newRes.getDateFrom().compareTo(old.getDateTo()) <= 0 ||
                newRes.getDateTo().compareTo(old.getDateFrom()) >= 0
                && newRes.getDateTo().compareTo(old.getDateTo()) <= 0;
    }

    public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

}
