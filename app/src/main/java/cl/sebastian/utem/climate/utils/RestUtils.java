package cl.sebastian.utem.climate.utils;

import cl.sebastian.utem.climate.persistence.model.Climate;
import cl.sebastian.utem.climate.persistence.model.Station;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestUtils implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String WD = "Wind direction:";
    private static final String WS = "Wind speed    :";
    private static final String VI = "Visibility    :";
    private static final String TM = "Temperature   :";
    private static final String DP = "Dewpoint      :";
    private static final String PS = "Pressure      :";
    private static final Double KT2KMH = 1.852;
    private static final Logger LOGGER = LoggerFactory.getLogger(RestUtils.class);

    private RestUtils() {
        throw new IllegalStateException();
    }

    public static Climate convert(final Station station, final String cmdOutput) {
        Climate climate = null;
        try {
            if (station != null && StringUtils.isNotBlank(cmdOutput)) {
                String windDirection = StringUtils.EMPTY;
                Double windSpeed = null;
                Double visibility = null;
                Double temperature = null;
                Double dewpoint = null;
                Double presure = null;

                LOGGER.info("{}", cmdOutput);
                try (BufferedReader reader = new BufferedReader(new StringReader(cmdOutput))) {
                    String line;
                    while ((line = StringUtils.trimToNull(reader.readLine())) != null) {
                        // Variable de datos
                        String data = StringUtils.EMPTY;

                        /**
                         * Si hay un ':' quiere decir que hay datos para
                         * interpretar
                         */
                        if (StringUtils.contains(line, ":")) {
                            String[] split = StringUtils.split(line, ":");
                            if (split.length > 1) {
                                data = StringUtils.trimToEmpty(split[1]);
                            }
                        }

                        if (StringUtils.contains(line, WD)) {
                            // Dirección del viento
                            windDirection = data;

                        } else if (StringUtils.contains(line, WS)) {
                            // Velocidad del viento
                            windSpeed = NumberUtils.toDouble(data.replaceAll("[^\\.0123456789]", StringUtils.EMPTY)) * KT2KMH;
                        } else if (StringUtils.contains(line, VI)) {
                            // Visiblidad
                            visibility = NumberUtils.toDouble(data.replaceAll("[^\\.0123456789]", StringUtils.EMPTY));
                        } else if (StringUtils.contains(line, TM)) {
                            // Temperatura
                            temperature = NumberUtils.toDouble(data.replaceAll("[^\\.0123456789]", StringUtils.EMPTY));
                        } else if (StringUtils.contains(line, DP)) {
                            // Rocío
                            dewpoint = NumberUtils.toDouble(data.replaceAll("[^\\.0123456789]", StringUtils.EMPTY));
                        } else if (StringUtils.contains(line, PS)) {
                            // Presión
                            presure = NumberUtils.toDouble(data.replaceAll("[^\\.0123456789]", StringUtils.EMPTY));
                        } else {
                            LOGGER.debug("{}", line);
                        }
                    }
                } catch (IOException exc) {
                    LOGGER.error("Error procesando lineas");
                }

                climate = new Climate();
                climate.setCreated(new Date());
                climate.setDewpoint(new BigDecimal(dewpoint).setScale(2, RoundingMode.HALF_UP));
                climate.setPressure(new BigDecimal(presure).setScale(2, RoundingMode.HALF_UP));
                climate.setStation(station);
                climate.setTemperature(new BigDecimal(temperature).setScale(2, RoundingMode.HALF_UP));
                climate.setVisibility(new BigDecimal(visibility).setScale(2, RoundingMode.HALF_UP));
                climate.setWindDirection(windDirection);
                climate.setWindSpeed(new BigDecimal(windSpeed).setScale(2, RoundingMode.HALF_UP));

            }
        } catch (Exception e) {
            climate = null;
            LOGGER.error("Error al convertir objeto: {}", e.getMessage());
            LOGGER.debug("Error al convertir objeto: {}", e.getMessage(), e);
        }
        return climate;
    }

}
