package cdi.dao;

import entities.Airport;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AirportDAO
{
    @Inject
    private EntityManager em;

    public void create(Airport airport) {
        em.persist(airport);
    }

    public List<Airport> getAirports() {
        return em.createNamedQuery("Airport.findAll", Airport.class).getResultList();
    }
}
