package model.entities;

import model.exception.DomainException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {

    private Integer roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;

    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) throws DomainException{
        if (!checkOut.isAfter(checkIn)){
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getChekIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public long duration(){
        Duration d1 = Duration.between(checkIn.atTime(0, 0), checkOut.atTime(0, 0));
        return d1.toDays();
    }

    //throws indica que o metodo pode lançar a exceção sinalizada, isso propaga a exceção
    //através da pilha de chamada dos métodos
    public void updateDates(LocalDate checkIn, LocalDate checkOut) throws DomainException{
        if (checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now())){
            throw new DomainException("Reservation dates for update must be future dates");
            //lançada exceção para entrada de dados inválidas
        }
        if (!checkOut.isAfter(checkIn)){
             throw new DomainException("Check-out date must be after check-in date");
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString(){
        return "Room "
                + roomNumber
                + ", check-in: "
                + fmt.format(checkIn)
                + ", check-out: "
                + fmt.format(checkOut)
                + ", "
                + duration()
                + " nights";
    }
}
