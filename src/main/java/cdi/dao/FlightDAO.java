package cdi.dao;

import entities.Flight;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by jnarkute on 3/31/2017.
 */
@ApplicationScoped
public class FlightDAO
{
    @Inject
    private EntityManager em;

    public void create(Flight flight) {
        em.persist(flight);
    }
    public List<Flight> getFlights() {
        return em.createNamedQuery("Flight.findAll", Flight.class).getResultList();
    }
}
