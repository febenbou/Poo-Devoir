package Modele;

/**
 *
 * @author febenbou
 */
public class Seat {

    private SeatID seatNum;
    private boolean isBooked;

    public Seat(SeatID seatNum, boolean isBooked) {
        this.seatNum = seatNum;
        this.isBooked = isBooked;
    }

    public SeatID getSeatNum() {
        return seatNum;
    }

    public boolean getStatus() {
        return isBooked;
    }

    public void setStatus(boolean isBooked) {
        this.isBooked = isBooked;

    }
}
