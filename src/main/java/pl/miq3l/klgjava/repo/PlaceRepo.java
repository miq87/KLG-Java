package pl.miq3l.klgjava.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.miq3l.klgjava.model.Place;

@Repository
public interface PlaceRepo extends JpaRepository<Place, Long> {
}
