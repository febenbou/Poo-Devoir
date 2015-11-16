package Modele;

public class AirlineFactory {
	
	public Airline createAirline(String s){
		return new Airline(s);
	}
}
