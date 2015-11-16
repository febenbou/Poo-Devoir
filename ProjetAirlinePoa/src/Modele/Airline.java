
package Modele;

/**
 *
 * @author febenbou
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class Airline {

    private String name;
    private ArrayList<Flight> flights;

    public Airline() {
    }

    public Airline(String name) {
        this.name = name;
        this.flights = new ArrayList<>();
    }

    public Airline(String name, ArrayList<Flight> allFlights) {
        this.name = name;
        this.flights = allFlights;
    }

    public Flight createFlight(Airport org, Airport dst, Calendar calendar, String id) {
        if (this.findFlight(id) == null) { //eviter les doublons
            FlightFactoryBuilder ffb = new FlightFactoryBuilder();
            ffb.AjoutAirline(this);
            ffb.AjoutOrigine(org);
            ffb.AjoutDestination(dst);
            ffb.AjoutID(id);
            ffb.AjoutDate(calendar);
            ffb.AjoutSections();
            this.getFlights().add(ffb.createFlight());
            System.out.println("Vol " + id + "crée");

            return ffb.createFlight();
        } else {
            Flight f = null;
            return f;
        }
    }

    public Flight findFlight(String idFlight) {
        Flight f = null;
        if (!this.flights.isEmpty()) {
        	Iterator<Flight> it = flights.iterator();
        	while (it.hasNext()) {
        		Flight flight= it.next();
                if (flight.getFlightID().equals(idFlight)) {
                    f = flight;
                }
            }
        }
        return f;
    }

    public ArrayList<Flight> getAvailableFlight(Airport org, Airport dst) {
        ArrayList<Flight> listFlight = new ArrayList<Flight>();
        Iterator<Flight> it = listFlight.iterator();
        while (it.hasNext()) {
        	Flight flight= it.next();
            if (flight.getDestination() == dst && flight.getOrigine() == org) {
                if (flight.hasSeats()) {
                	listFlight.add(flight);
                }
            }
        }
        return listFlight;

    }

    public void bookFlight(String fl, SeatClass s, int row, char col) {
        Flight f = findFlight(fl);
        f.bookSeat(s, row, col);
    }

    public void createSection(String idF, int row, int col, SeatClass seat) {
        Flight flight = this.findFlight(idF);
        boolean bool = false;
        if (flight !=null)
        {
        	ArrayList<FlightSection> sectionFlight=flight.getArraySections();
            if(sectionFlight!=null){
            	Iterator<FlightSection> it = sectionFlight.iterator();
            	 while (it.hasNext())
                {
                    if(it.next().getSeatClass()==seat)
                    {
                            System.out.println("Le vol " +idF+ " continent déja la section " + seat.name() );
                            bool=true;
                       }
                }
            }
        
        else {
            System.out.println("Impossible de créer la section " + seat.name() + " car le vol " + idF+ "n'existe pas");
        } 
        
            if(bool==false)
            {
            	flight.createSection(row, col, seat);
                System.out.println("La section " + seat.name() + " crée pour le vol " + idF);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Flight> getFlights() {
        return this.flights;
    }
}
