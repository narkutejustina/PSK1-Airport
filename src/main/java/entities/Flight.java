package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "Flight")
@NamedQueries({
        @NamedQuery(name = "Flight.findAll", query = "SELECT a FROM Flight a")})
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Duration")
    private int duration;

    @Column(name = "DepartureDate")
    private Date date;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;

    @ManyToMany(mappedBy = "flights")
    private List<Passenger> passengers = new ArrayList<>();

    @JoinColumn(name = "arrivalAirportId", referencedColumnName = "Id")
    @ManyToOne
    private Airport arrivalAirport;

    @JoinColumn(name = "departureAirportId", referencedColumnName = "Id")
    @ManyToOne
    private Airport departureAirport;
}
