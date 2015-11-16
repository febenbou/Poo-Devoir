/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author febenbou
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;


public class Flight {

    private Airline airline;
    private Calendar flightDate;
    private String flightID;
    private Airport Origine;
    private Airport Destination;
    private ArrayList<FlightSection> arrayListSections;

    public Flight() {}
    
   
    public boolean hasSeats() {
    	Iterator<FlightSection> it = arrayListSections.iterator();
    	while (it.hasNext()) {
        if (it.next().hasAvailableSeats()) {
            return true;
        }
    	}
        return false;
    }

    public boolean hasSection() {
    	Iterator<FlightSection> it = arrayListSections.iterator();
    	while (it.hasNext()) {
            if (it.next() != null) {
                return true;
            }
        }
        return false;
    }

    public boolean createSection(int row, int col, SeatClass sect) {
        try {
        	FlightSectionFactory flightSectionFactory= new FlightSectionFactory();
        	FlightSection section = flightSectionFactory.createFlightSection(row, col,sect);

            this.arrayListSections.add(section);
            return true;
        } 
        catch (Exception e) {
            return false;
        }
    }

    public void bookSeat() 
    {
        boolean disponible = false;

        Iterator<FlightSection> it = arrayListSections.iterator();
        
        while (it.hasNext()) {
        	if (it.next().hasAvailableSeats())
        		disponible = true;
        }
        
        if (disponible == true)  { 
        	System.out.println("Il y a encore des places disponible");
        } else       
        	System.out.println("pas de place disponible");
        
    }
  
    public void bookSeat(SeatClass seat, int row, char col) {
        FlightSection fSection=null;
        boolean bool=false;  
        
        Iterator<FlightSection> it = arrayListSections.iterator();
        
        while (it.hasNext()) {
        	fSection=it.next();
            if (fSection.getSeatClass()== seat) {
                bool=true;
                SeatID seatID = new SeatID(row, col);
                if (fSection.bookSeat(seatID))
                {
                    return;
                }
            }
        }
        
        if(bool==false) {
                System.out.println("La section " + seat.name() + " n'existe pas");
            }
             
        
    }

    public FlightSection findSection(FlightSection section) 
    {
    	Iterator<FlightSection> it = arrayListSections.iterator();
    	while (it.hasNext()) {
            if (it.next() == section)
            {
                return section;
            }
        }
        return null;
    }
    
    public Calendar getDate() 
    {
        return flightDate;
    }

    public void setDate(Calendar flightDate)
    {
        this.flightDate = flightDate;
    }

    public String getFlightID() 
    {
        return flightID;
    }

    public void setFlightID(String flightID) 
    {
        this.flightID = flightID;
    }

    public Airport getOrigine() 
    {
        return Origine;
    }

    public void setOrigine(Airport origine)
    {
        Origine = origine;
    }

    public Airport getDestination()
    {
        return Destination;
    }

    public void setDestination(Airport destination) 
    {
        this.Destination = destination;
    }

    public ArrayList<FlightSection> getArraySections()
    {
        return arrayListSections;
    }

    public void setArraySections(ArrayList<FlightSection> arraySect)
    {
        this.arrayListSections = arraySect;
    }

    public Airline getAirline()
    {
        return airline;
    }

    public void setAirline(Airline airline)
    {
        this.airline = airline;
    }

}

