package mybatis;


import mybatis.Entities.Flight;
import mybatis.dao.FlightMapper;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model // tas pats kaip: @Named ir @RequestScoped
public class useCaseController {

    @Inject
    private FlightMapper flightMapper;

    public List<Flight> getFlights() {

        List<Flight> fl = flightMapper.selectAll();
        return fl;
    }
}
