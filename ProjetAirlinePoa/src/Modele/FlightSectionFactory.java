package Modele;

public class FlightSectionFactory {

	public FlightSection createFlightSection(int row, int col,SeatClass sect){
		return new FlightSection(row, col,sect);
	}
}
