package cl.sebastian.utem.climate.persistence.repository;

import cl.sebastian.utem.climate.persistence.model.Country;
import cl.sebastian.utem.climate.persistence.model.Station;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    public List<Station> findByCountry(Country country);

    public Station findByIcao(String icao);
}
