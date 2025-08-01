package modelo.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {

    private Integer roomNumber;
    private LocalDate chekIn;
    private LocalDate checkOut;

    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reservation(Integer roomNumber, LocalDate chekIn, LocalDate checkOut) {
        this.roomNumber = roomNumber;
        this.chekIn = chekIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getChekIn() {
        return chekIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public long duration(){
        Duration d1 = Duration.between(chekIn.atTime(0, 0), checkOut.atTime(0, 0));
        return d1.toDays();
    }

    public String updateDates(LocalDate checkIn, LocalDate checkOut){
        if (checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now())){
            return "Reservation dates for update must be future dates";
        }
        if (!checkOut.isAfter(checkIn)){
             return "Check-out date must be after check-in date";
        }
        this.chekIn = checkIn;
        this.checkOut = checkOut;
        return null;
    }

    @Override
    public String toString(){
        return "Room "
                + roomNumber
                + ", check-in: "
                + fmt.format(chekIn)
                + ", check-out: "
                + fmt.format(checkOut)
                + ", "
                + duration()
                + " nights";
    }
}
