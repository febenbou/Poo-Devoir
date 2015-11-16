
package Modele;

/**
 *
 * @author febenbou
 */

import java.util.ArrayList;
import java.util.Calendar;


public class FlightFactoryBuilder {
	
    private Flight f;
    
    public FlightFactoryBuilder() {
    	 f = new Flight();
    }
    
    public Flight createFlight() {
        return this.f;
    }
    
    public void AjoutOrigine(Airport origine) {
        f.setOrigine(origine);
    }

    public void AjoutDestination(Airport destination) {
        f.setDestination(destination);
    }

    public void AjoutAirline(Airline airline) {
        f.setAirline(airline);
    }

    public void AjoutID(String id) {
        f.setFlightID(id);
    }

    public void AjoutDate(Calendar date) {
        f.setDate(date);
    }

    public void AjoutSections() {
        f.setArraySections(new ArrayList<FlightSection>());
    }

    
}

