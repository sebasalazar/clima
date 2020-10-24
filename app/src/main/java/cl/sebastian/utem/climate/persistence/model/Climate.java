package cl.sebastian.utem.climate.persistence.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "climates")
public class Climate extends PkEntityBase {

    @JoinColumn(name = "station_fk", referencedColumnName = "pk", nullable = false)
    @ManyToOne
    private Station station = null;

    @Column(name = "wind_direction", nullable = false)
    private String windDirection = null;

    @Column(name = "wind_speed", nullable = false)
    private BigDecimal windSpeed = BigDecimal.ZERO;

    @Column(name = "visibility", nullable = false)
    private BigDecimal visibility = BigDecimal.ZERO;

    @Column(name = "temperature", nullable = false)
    private BigDecimal temperature = BigDecimal.ZERO;

    @Column(name = "dewpoint", nullable = false)
    private BigDecimal dewpoint = BigDecimal.ZERO;

    @Column(name = "pressure", nullable = false)
    private BigDecimal pressure = BigDecimal.ZERO;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created = new Date();

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
    }

    public BigDecimal getVisibility() {
        return visibility;
    }

    public void setVisibility(BigDecimal visibility) {
        this.visibility = visibility;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(BigDecimal dewpoint) {
        this.dewpoint = dewpoint;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public void setPressure(BigDecimal pressure) {
        this.pressure = pressure;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
