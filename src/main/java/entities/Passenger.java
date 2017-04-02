package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Passenger")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NamedQueries({
        @NamedQuery(name = "Passenger.findAll", query = "SELECT a FROM Passenger a"),
        @NamedQuery(name = "Passenger.findMatch", query = "SELECT a FROM Passenger a where a.personalCode = :pc")})
public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PC")
    private double personalCode;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @JoinTable(name = "Flight_passenger",
        joinColumns = {
                @JoinColumn(name = "passengerPc", referencedColumnName = "Pc")},
        inverseJoinColumns = {
                @JoinColumn(name = "flightId", referencedColumnName = "Id")})

    @ManyToMany(cascade ={CascadeType.PERSIST, CascadeType.MERGE})
    private List<Flight> flights = new ArrayList<>();
}



