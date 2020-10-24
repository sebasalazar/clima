package cl.sebastian.utem.climate.persistence.repository;

import cl.sebastian.utem.climate.persistence.model.Climate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimateRepository extends JpaRepository<Climate, Long> {

}
