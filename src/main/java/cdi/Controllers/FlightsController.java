package cdi.Controllers;


import cdi.dao.AirportDAO;
import cdi.dao.FlightDAO;
import entities.Airport;
import entities.Flight;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model // tas pats kaip: @Named ir @RequestScoped
@Slf4j
public class FlightsController {

    @Getter
    private Flight newFlight = new Flight();
    @Getter
    private List<Airport> departureAirports;
    @Getter
    private List<Airport> arrivalAirports;

    @Inject
    private AirportDAO airportDAO;
    @Inject
    private FlightDAO flightDAO;

    @PostConstruct
    public void init() {
        arrivalAirports = departureAirports = airportDAO.getAirports();
    }
    @Transactional
    public void addFlight() {
        flightDAO.create(newFlight);
        log.info("SUCCESS: new flight added");
    }

    public List<Flight> getFlights() {
        return flightDAO.getFlights();
    }

    public void onDepartureAirportChange() {
        arrivalAirports = airportDAO.getAirports();
        arrivalAirports.remove(newFlight.getDepartureAirport());
    }
}
