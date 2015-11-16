
package Vue;

/**
 *
 * @author febenbou
 */

import Controleur.*;
import Modele.*;


public class ClientAMS {

  
    public static void main(String[] args) {

        SystemManager res = SystemManager.getInstance();
        
        // Ai rp o r t s
        res.createAirport("DEN");
        res.createAirport("DFW");
        res.createAirport("LON");
        res.createAirport("DEN");
        res.createAirport("CDG");
        res.createAirport("JPN");
        //res.createAirport("DEN","Probleme"); // Pb d'unicité
        res.createAirport("DE"); // Invalide
        res.createAirport("DEH");
        res.createAirport("DRIrdn3"); // Invalide
        
        // A i r l i n e s
        res.createAirline("DELTA");
        res.createAirline("AIRFR");
        res.createAirline("AMER");
        res.createAirline("JET");
        res.createAirline("DELTA");
        res.createAirline("SWEST");
        res.createAirline("FRONTIER"); // Invalide
        
        // F l i g h t s
        res.createFlight("AIRFR", "DEN", "LON", 2008, 8, 9, "467");
        res.createFlight("JET", "DEN", "JPN", 2009, 11, 12, "113");
        res.createFlight("DELTA", "DEN", "DEH", 2009, 8, 9, "567");
        res.createFlight("FRONTIER", "DEN", "NCE", 2010 , 9, 8, "567"); 
        res.createFlight("AMER", "LON", "CDG", 2009, 8, 9, "667");
        res.createFlight("DELTA", "DEN", "DEH", 2008, 11, 12, "123");
        res.createFlight("JET", "DEN", "DEH", 2009, 8, 9, "112");
        res.createFlight("AIRFR", "LON", "DEN", 2009, 11, 12, "423");
        res.createFlight("JET", "DEN", "LON", 2009, 8, 9, "133");
        res.createFlight("SWEST", "LON", "JPN", 2008, 11, 12, "597");
        res.createFlight("SWEST", "JPN", "LON", 2008, 8, 9, "193");
        res.createFlight("AMER", "LON", "JPN", 2008, 8, 9, "891");
   
   

        // S e c ti o ns
        res.createSection("DELTA","81",1,1,SeatClass.BUSI);
        res.createSection("JET", "133", 2, 2, SeatClass.ECO);
        res.createSection("SWSERTT", "153", 5, 5, SeatClass.ECO ); 
        res.createSection("JET", "667", 2, 3, SeatClass.FIRST);    
        res.createSection("DELTA", "153", 2, 3, SeatClass.ECO);
        res.createSection("JET", "153", 1, 3, SeatClass.ECO);
        res.createSection("DELTA", "153",2, 3, SeatClass.BUSI);         
        res.createSection("AMER", "667", 101, 11, SeatClass.BUSI); 
        res.createSection("DELTA", "667", 1, 2, SeatClass.BUSI);

        res.findAvailableFlights("DEN", "LON");
        res.findAvailableFlights("X5XX", "YYTY");
        res.findAvailableFlights("XXX", "LON");
        res.findAvailableFlights("DEN", "YYYFRF");
        res.findAvailableFlights("LON", "DEN");//erreur
       
       
        res.bookSeat("DELTA", "123", SeatClass.BUSI, 2, 'A');
        res.bookSeat("DELTA", "123", SeatClass.BUSI, 1, 'A');
        res.bookSeat("DELTA", "123", SeatClass.BUSI, 1, 'A');
        res.bookSeat("DELTA", "123", SeatClass.BUSI, 1, 'B');
        res.bookSeat("DELTA", "123", SeatClass.BUSI, 1, 'B');
        res.bookSeat("DELTA", "123", SeatClass.ECO, 1, 'A');
        res.bookSeat("DELTA", "123", SeatClass.ECO, 2, 'B');
        res.bookSeat("DELTA", "123", SeatClass.FIRST, 1, 'C');
       
        res.displaySystemDetails();        
    }

}