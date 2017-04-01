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
        @NamedQuery(name = "Passenger.findAll", query = "SELECT a FROM Passenger a")})
public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Age")
    private int age;

    @Column(name = "Nationality")
    private String nationality;

    @JoinTable(name = "Flight_passenger",
            joinColumns = {
                @JoinColumn(name = "flightId", referencedColumnName = "ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "passengerId", referencedColumnName = "ID")})
    @ManyToMany
    private List<Flight> flights = new ArrayList<>();
}



