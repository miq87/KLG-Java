package pl.miq3l.klgjava.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.miq3l.klgjava.model.Reservation;
import pl.miq3l.klgjava.service.ReservationService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/all")
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public Reservation findById(@PathVariable("id") Long id) {
        return reservationService.findById(id);
    }

    @PostMapping
    public Reservation save(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @PutMapping("/{id}")
    public Reservation put(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.put(id, reservation);
    }

    @PatchMapping("/{id}")
    public Reservation patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return reservationService.patch(id, fields);
    }

    @GetMapping("/tenant")
    public List<Reservation> getReservationsByTenantName(@RequestParam String name) {
        return reservationService.getReservationsByTenantName(name);
    }

    @GetMapping("/landlord")
    public List<Reservation> getReservationsByLandlordName(@RequestParam String name) {
        return reservationService.getReservationsByLandlordName(name);
    }

    @GetMapping("/place")
    public List<Reservation> getReservationsByPlaceName(@RequestParam String name) {
        return reservationService.getReservationsByPlaceName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
    }

}
