package cl.sebastian.utem.climate.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stations")
public class Station extends PkEntityBase {

    @JoinColumn(name = "country_fk", referencedColumnName = "pk", nullable = false)
    @ManyToOne
    private Country country = null;

    @Column(name = "icao", nullable = false)
    private String icao = null;

    @Column(name = "name", nullable = false)
    private String name = null;

    @Column(name = "latitude", nullable = false)
    private Double latitude = null;

    @Column(name = "longitude", nullable = false)
    private Double longitude = null;

    @Column(name = "elevation", nullable = false)
    private Integer elevation = null;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }
}
