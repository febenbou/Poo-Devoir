package Modele;

public class AirportFactory {

	public Airport createAirport(String s){
		
	return new Airport(s);
	
	}
}