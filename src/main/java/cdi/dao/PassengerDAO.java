package cdi.dao;

import entities.Passenger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by jnarkute on 3/31/2017.
 */
@ApplicationScoped
public class PassengerDAO
{
    @Inject
    private EntityManager em;

    public void create(Passenger passenger) {
        em.persist(passenger);
    }
    public void merge(Passenger passenger) {
        em.merge(passenger);
    }

    public List<Passenger> getPassengers() {
        return em.createNamedQuery("Passenger.findAll", Passenger.class).getResultList();
    }

    public Passenger getMatch(double psngCode) {
        return em.createNamedQuery("Passenger.findMatch", Passenger.class)
        .setParameter("pc", psngCode )
        .getResultList().get(0);
    }
}
