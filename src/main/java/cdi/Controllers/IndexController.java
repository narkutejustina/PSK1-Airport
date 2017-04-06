package cdi.Controllers;

import cdi.dao.AirportDAO;
import entities.Airport;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model // tas pats kaip: @Named ir @RequestScoped
public class IndexController {

    @Getter
    private Airport airport = new Airport();

    @Inject
    private AirportDAO airportDAO;

    public List<Airport> getAirports() {
        return airportDAO.getAirports();
    }

}
