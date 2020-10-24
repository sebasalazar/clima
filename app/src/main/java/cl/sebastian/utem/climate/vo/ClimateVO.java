package cl.sebastian.utem.climate.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClimateVO implements Serializable {

    private static final long serialVersionUID = 7667723156992729088L;

    private String country = null;
    private String station = null;
    private String windDirection = null;
    private BigDecimal windSpeed = BigDecimal.ZERO;
    private BigDecimal visibility = BigDecimal.ZERO;
    private BigDecimal temperature = BigDecimal.ZERO;
    private BigDecimal dewpoint = BigDecimal.ZERO;
    private BigDecimal pressure = BigDecimal.ZERO;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
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
}
