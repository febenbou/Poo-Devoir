package Modele;

/**
 *
 * @author febenbou
 */


public class FlightSection {

    private SeatClass section;
    private int row;
    private int column;
    private Seat[][] seatTable2D;

    public FlightSection(int row, int col,SeatClass sect) {
        this.row = row;
        this.column = col;
        this.section = sect;
        seatTable2D = new Seat[row][column];
    }

    public boolean hasAvailableSeats() {
        for (int i = 0; i < this.seatTable2D.length; i++) {
            for (int j = 0; j < this.seatTable2D[i].length; j++) {
                if (seatTable2D[i][j] == null||(!seatTable2D[i][j].getStatus())) {
                    return true;
                } 
            }
        }
        return false;
    }
    
    public void bookSeat() {
        if (this.hasAvailableSeats()==true)
        {
            for (int i = 0; i < seatTable2D.length; i++)
            {
                for (int j = 0; j < seatTable2D[i].length; j++)
                {
                    if (!seatTable2D[i][j].getStatus())
                    {
                        seatTable2D[i][j].setStatus(true);
                    }
                    return;
                }
            }
        } 
        else
        {
            System.err.println("Aucune place n'est disponible");
        }
    }
    
    public int ConvertColumInNumber(char lettre) {
        char A = 'A';
        return ((int) lettre - (int) A + 1);
    }

    public boolean bookSeat(SeatID idSeat) {
        if (this.hasAvailableSeats()) {
            int row = idSeat.getRow();
            char column = idSeat.getColumn();
            int columnNumber = ConvertColumInNumber(column);

            if ((seatTable2D.length < row) || (seatTable2D[row-1].length < columnNumber)) {
                System.out.println("le numéro de la ligne ou colonne est incorrect");
                return false;
            }
            else if (seatTable2D[row-1][columnNumber-1] == null) {
                this.seatTable2D[row-1][columnNumber-1] = new Seat(idSeat, true);
                System.out.println("la place " + row + column + " a été réservé avec succes");
                return true;
            }
            else if (seatTable2D[row-1][columnNumber-1].getStatus() == false) {
                this.seatTable2D[row][columnNumber].setStatus(true);
                System.out.println("la place " + row + column + " a été réservé avec succes");
                return true;
            }            
            else if (seatTable2D[row-1][columnNumber-1].getStatus() == true) {
                System.out.println("La place " + row + column + " est deja reservée");
                return true;
            }
        }
        System.out.println("Aucune place n'est disponible dans cette section");
        return false;
    }


     public int getRow() {
        return row;

    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.column;
    }

    public void setCol(int colUMN) {
        this.column = colUMN;
    }

    public SeatClass getSeatClass() {
        return section;
    }

    public void setSection(SeatClass section) {
        this.section = section;
    }

    public Seat[][] getSeat() {
        return seatTable2D;
    }

    public void setSeat(Seat[][] seat) {
        seatTable2D = seat;
    }
}
