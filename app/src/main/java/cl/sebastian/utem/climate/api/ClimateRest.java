package cl.sebastian.utem.climate.api;

import cl.sebastian.utem.climate.dto.CmdDTO;
import cl.sebastian.utem.climate.persistence.model.Climate;
import cl.sebastian.utem.climate.persistence.model.Station;
import cl.sebastian.utem.climate.persistence.repository.StationRepository;
import cl.sebastian.utem.climate.utils.CmdUtils;
import cl.sebastian.utem.climate.utils.RestUtils;
import cl.sebastian.utem.climate.vo.ErrorVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/climate", consumes = {"application/json; charset=utf-8"}, produces = {"application/json; charset=utf-8"})
public class ClimateRest {

    @Autowired
    private transient StationRepository stationRepository;

    @GetMapping(value = "/{station}/status", consumes = {"*/*"}, produces = {"application/json; charset=utf-8"})
    public ResponseEntity climateByStation(@PathVariable(name = "station") String station) {

        if (StringUtils.isBlank(station)) {
            return new ResponseEntity(new ErrorVO("Faltan datos requeridos para compeltar su petición"), HttpStatus.BAD_REQUEST);
        }

        String icao = StringUtils.upperCase(StringUtils.trimToEmpty(station));
        Station stationResult = stationRepository.findByIcao(icao);
        if (stationResult == null) {
            return new ResponseEntity(new ErrorVO(String.format("No se ha encontrado la estación con código %s", icao)), HttpStatus.NOT_FOUND);
        }

        String cmd = String.format("metar -d %s", icao);
        CmdDTO executed = CmdUtils.execute(cmd);
        if (executed == null || executed.getExitCode() != 0) {
            return new ResponseEntity(new ErrorVO("Ocurrió un error al procesar su solicitud"), HttpStatus.PRECONDITION_FAILED);
        }

        Climate climate = RestUtils.convert(stationResult, executed.getOutput());
        if (climate == null) {
            return new ResponseEntity(new ErrorVO("No fue posible interpretar la respuesta"), HttpStatus.PRECONDITION_FAILED);
        }

        return ResponseEntity.ok(climate);
    }
}
