package entities;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of="id")
@ToString(of="id")
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

    @ManyToMany(mappedBy = "flights",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Passenger> passengers = new ArrayList<>();

    @JoinColumn(name = "arrivalAirportId", referencedColumnName = "Code")
    @ManyToOne
    private Airport arrivalAirport;

    @JoinColumn(name = "departureAirportId", referencedColumnName = "Code")
    @ManyToOne
    private Airport departureAirport;

    public String getStringDate(){
        return date.toString().substring(0,10);
    }
}
