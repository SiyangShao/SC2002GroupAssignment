package Model;

import java.io.Serializable;

public class Seat implements Serializable {
    protected int seatNo;
    protected int MovieID;

//    protected double basePrice;

    public Seat(int seatNo, int MovieID) {
        setSeatNo(seatNo);
        setMovieID(MovieID);
//        setBasePrice(basePrice);
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int movieID) {
        MovieID = movieID;
    }

//    public double getBasePrice() {
//        return basePrice;
//    }

//    public void setBasePrice(double basePrice) {
//        this.basePrice = basePrice;
//    }
}
