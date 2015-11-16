package Controleur;

import Modele.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author febenbou
 */
public class SystemManager {
	/** Instance unique  */
    private static SystemManager INSTANCE;
    
    private ArrayList<Airport> listAirports = null;
    private ArrayList<Airline> listAirlines = null;
    
    /** Constructeur privé */
    private SystemManager() {

    }

	/** Point d'accès pour l'instance unique du singleton */
    public static SystemManager getInstance() {
        if (INSTANCE != null) {
        	return INSTANCE;
        }else{
        INSTANCE = new SystemManager();
        return INSTANCE; 
        }
    }

    public void createAirport(String nom) {
        if (listAirports == null) {
        	listAirports = new ArrayList<Airport>();
        }
        if(nom.equals(""))
        {
            System.out.println("non pas valide");
        }
        else if(nom.length()==3)
        {	
        	AirportFactory airportF= new AirportFactory();
    		Airport airport = airportF.createAirport(nom);
            listAirports.add(airport);   
            System.out.println("Airport "+nom+ " crée");
        }
        else
        {
            System.out.println("Airport "+nom+ " non crée (erreur de parametrage)");
        }
        
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public void createAirline(String nom) {
        if (listAirlines == null) {
        	listAirlines = new ArrayList<Airline>();
        }
        if (nom.length()<=5 && nom.length()>0){
        	AirlineFactory airlineF = new AirlineFactory();
    		Airline airline = airlineF.createAirline(nom);
    		listAirlines.add(airline);
            System.out.println("compagnie "+nom+ " crée ");
        }
        else
        {
            System.out.println("Airline " + nom + " non crée (erreur de parametrage)");
        }
    }
    
    public void createFlight(String nameAirline, String org, String dst, int year, int month, int day, String idF) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.DAY_OF_MONTH, day);
        Airport origine = findAirport(org);
        Airport destination = findAirport(dst);
        Airline airline = findAirline(nameAirline);

        if (airline == null)
            System.out.println("vol non crée car la compagnie "+nameAirline+" n'existe pas dans le registre des compagnies");
        else if(origine == null || destination == null)
            System.out.println("l'aeroport de depart ou celui d'arriver n'existe pas dans le registre des aeroports");
        else 
        {
         airline.createFlight(origine, destination, date, idF);
        }
    }
    
    
    public void createSection(String nameAirline, String flightID, int row, int col, SeatClass seat) {

        Airline airline = findAirline(nameAirline);
        if (airline == null)
        {
            System.out.println("la compagnie " + nameAirline + " n'existe pas dans le registre des compagnies");
        } 
        
        else if(row>100 || col>10)
        {
            System.out.println("erreur de parametrage");
        }
        else 
        {
        	airline.createSection(flightID, row, col, seat);
        }
    }

    public void findAvailableFlights(String origine, String destination) {
        Airport orig = this.findAirport(origine);
        Airport dest = this.findAirport(destination);
        boolean bool=false;

        ArrayList<Flight> listFlights = new ArrayList<Flight>();
        for (int i = 0; i < getAirline().size(); i++) {
        	listFlights = this.getAirline().get(i).getAvailableFlight(orig, dest);
        	Iterator<Flight> it = listFlights.iterator();
            while(it.hasNext()){
            	Flight f = it.next();
                System.out.println("place disponible pour le vol numéro: " + f.getFlightID() + " qui va de"+orig.getName() + " à " + dest.getName());
                bool=true;
            }
        }
        
        if(listFlights.isEmpty()|| (orig==null || dest==null ) || bool==false)
        {
            System.out.println("Aucun vol de " + origine + "vers" +destination);
        }
    }

    public void bookSeat(String air, String flightID, SeatClass s, int row, char col) {
        Airline a = findAirline(air);
        if (a !=null)  {
            Flight flight = a.findFlight(flightID);
            if (flight == null) {
                System.out.println("Pas de vol correspondant");
            } else {
                flight.bookSeat(s, row, col);
            }
        }else {
            System.out.println("Airline non trouvé");
        }

    }

    public Airport findAirport(String nameAirport) {
        Airport airport = null;
        for (int i = 0; i < listAirports.size(); i++)
        {
            if (listAirports.get(i).getName().equals(nameAirport)) 
            {
            	airport = listAirports.get(i);
            }
        }
        if (airport == null)
        {
            System.out.println("Aeroport non trouvé");
            return null;
        } 
        else
        {
            return airport;
        }
    }

    public Airline findAirline(String nameAirline) {
        Airline airline = null;
        for (int i = 0; i < listAirlines.size(); i++) {
            if (listAirlines.get(i).getName().equals(nameAirline)) {
                airline = listAirlines.get(i);
            }

        }
        if (airline == null) {
            System.out.println("Airline introuvable ");
            return airline;
        } else {
            return airline;
        }
    }

    public Flight findFlight(ArrayList<Flight> f, String id) {
        Flight flight = null;
        for (int i = 0; i < f.size(); i++) {
            if (f.get(i).getFlightID().equals(id)) {
            	flight = f.get(i);
            }

        }
        if (flight == null) {
            System.out.println("Vol introuvable");
            return flight;
        } else {
            return flight;
        }
    }
    
    public void displaySystemDetails() {
        System.out.println("\n ----------------------------Details des traitements----------------------------------\n");
        System.out.println("Liste des Aeroports crées : \n");
        for (int i = 0; i < listAirports.size(); i++) {
            System.out.println("- " + listAirports.get(i).getName());
        }
        System.out.println("\n");
        System.out.println("Liste des Compagnies crées : \n");
        for (int i = 0; i < listAirlines.size(); i++) {
            System.out.println("- " + listAirlines.get(i).getName());
            System.out.println("  Liste des vols pour cette companie");
            
            for (int j = 0; j < listAirlines.get(i).getFlights().size(); j++) {
                System.out.println(" - Identifiant du vol : " + listAirlines.get(i).getFlights().get(j).getFlightID() + "- De :"
                        + listAirlines.get(i).getFlights().get(j).getOrigine().getName() + " vers :" + listAirlines.get(i).getFlights().get(j).getDestination().getName()
                        + "   Date : " + listAirlines.get(i).getFlights().get(j).getDate().get(Calendar.DAY_OF_MONTH) + "/"
                        + listAirlines.get(i).getFlights().get(j).getDate().get(Calendar.MONTH) + "/"
                        + listAirlines.get(i).getFlights().get(j).getDate().get(Calendar.YEAR));
            }
        }

    }

    public ArrayList<Airport> getAirport() {
        return listAirports;
    }

    public ArrayList<Airline> getAirline() {
        return listAirlines;
    }
    public void initAll(){
        listAirports=null;
        listAirports=null;
    }
    
}
