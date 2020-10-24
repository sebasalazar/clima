package cl.sebastian.utem.climate.persistence.repository;

import cl.sebastian.utem.climate.persistence.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    public Country findByIso2IgnoreCase(String iso2);
}
