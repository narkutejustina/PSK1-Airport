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

@Model
@Slf4j
public class FlightsController {

    @Getter
    private Flight newFlight = new Flight();
    @Getter
    private List<Airport> departureAirports;
    @Getter
    private List<Airport> arrivalAirports ;
    @Getter
    private List<Flight> flights;

    @Inject
    private AirportDAO airportDAO;
    @Inject
    private FlightDAO flightDAO;
    @PostConstruct
    public void init() {
        flights = flightDAO.getFlights();
        arrivalAirports = departureAirports = airportDAO.getAirports();
    }

    @Transactional
    public void addFlight() {
        Flight existing = getExisting(newFlight);
        if (existing == null)
        {
            flightDAO.create(newFlight);
            return;
        }
    }

    public void onDepartureAirportChange() {
        arrivalAirports = airportDAO.getAirports();
        arrivalAirports.remove(newFlight.getDepartureAirport());
    }

    private Flight getExisting(Flight flight) {
        for (Flight f : flights) {
            if (f.getArrivalAirport().getCode() == flight.getArrivalAirport().getCode()
                    && f.getDepartureAirport().getCode() == flight.getDepartureAirport().getCode()
                    && f.getDate() == flight.getDate())
                return f;
        }
        return null;
    }
}
