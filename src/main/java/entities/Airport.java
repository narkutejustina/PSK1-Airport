package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "Airport")
@NamedQueries({
        @NamedQuery(name = "Airport.findAll", query = "SELECT a FROM Airport a")})
public class Airport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id")
    private String code;

    @Column(name = "Name")
    private String title;

    @Column(name = "City")
    private String city;

    @Column(name = "Country")
    private String country;

    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivalFlights = new ArrayList<>();

    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departureFlights = new ArrayList<>();
}

