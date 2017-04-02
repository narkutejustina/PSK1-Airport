package cdi.Controllers;


import cdi.dao.FlightDAO;
import cdi.dao.PassengerDAO;
import entities.Flight;
import entities.Passenger;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model // tas pats kaip: @Named ir @RequestScoped
@Slf4j
public class PassengersController {

    @Getter
    private Passenger newPassenger = new Passenger();
    @Getter
    @Setter
    private Flight selectedFlight = new Flight();
    @Getter
    private List<Flight> flights;
    @Getter
    private List<Passenger> passengers;
    @Inject
    private FlightDAO flightDAO;
    @Inject
    private PassengerDAO passengerDAO;

    @PostConstruct
    public void init() {
        flights = flightDAO.getFlights();
        passengers = passengerDAO.getPassengers();
    }
    @Transactional
    public void addPassenger(){
        Passenger existing = getExisting(newPassenger);

        if ( existing == null)
        {
            newPassenger.getFlights().add(selectedFlight);
            passengerDAO.create(newPassenger);

            return;
        }
        newPassenger.setFlights(existing.getFlights());
        newPassenger.getFlights().add(selectedFlight);
        //        existing.getFlights().add(selectedFlight);
        passengerDAO.merge(newPassenger);
    }

    public String getLastFlightString(double code) throws Exception{
        Passenger match=passengerDAO.getMatch(code);
        if(match==null)
            throw new Exception("Couldn't find exisitng Passenger");

        int size = match.getFlights().size();
        if(size == 0)
            return "-";

        Flight lastFlight = match.getFlights().get(match.getFlights().size()-1);
        return lastFlight.getStringDate() + ": " + lastFlight.getDepartureAirport().getCode() + "-" +
                lastFlight.getArrivalAirport().getCode();
    }

    private Passenger getExisting(Passenger p){
        for(Passenger psng : passengers){
            if(psng.getPersonalCode() == p.getPersonalCode())
                return psng;
        }
        return null;
    }
}
