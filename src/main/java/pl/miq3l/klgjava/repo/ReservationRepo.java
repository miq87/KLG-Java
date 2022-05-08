package pl.miq3l.klgjava.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.miq3l.klgjava.model.Reservation;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r JOIN r.tenant t WHERE t.name = :name")
    List<Reservation> getReservationsByTenantName(@Param("name") String name);

    @Query("SELECT r FROM Reservation r JOIN r.place p WHERE p.name = :name")
    List<Reservation> getReservationsByPlaceName(@Param("name") String name);

    @Query("SELECT r FROM Reservation r JOIN r.landlord l WHERE l.name = :name")
    List<Reservation> getReservationsByLandlordName(@Param("name") String name);
}
