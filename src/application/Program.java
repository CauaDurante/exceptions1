package application;

import model.entities.Reservation;
import model.exception.DomainException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            System.out.print("Room number: ");
            int number = sc.nextInt();
            System.out.print("Check-in date (dd/MM/yyyy): ");
            LocalDate checkIn = LocalDate.parse(sc.next(), fmt1);
            System.out.print("Check-out date (dd/MM/yyyy): ");
            LocalDate checkOut = LocalDate.parse(sc.next(), fmt1);

            Reservation reservation = new Reservation(number, checkIn, checkOut);
            System.out.println("Reservation: " + reservation);

            System.out.println();
            System.out.println("Enter data to update the reservation: ");
            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkIn = LocalDate.parse(sc.next(), fmt1);
            System.out.print("Check-out date (dd/MM/yyyy): ");
            checkOut = LocalDate.parse(sc.next(), fmt1);

            reservation.updateDates(checkIn, checkOut);

            System.out.println("Reservation: " + reservation);
        }
        catch (DateTimeParseException e){
            System.out.println("Invalid date format");
        }
        catch (IllegalArgumentException e){
            System.out.println("Error in reservation: " + e.getMessage());
            //capturada a exceção do metodo updateDates, ele imprime a mensagem que consta no metodo
        }
        catch (DomainException e){ //captura exceção criada
            System.out.println("Error in reservation: " + e.getMessage());
        }
        catch (RuntimeException e){ //captura qualquer exceção e impede a quebra de execução
            System.out.println("Unexpected error");
        }

        sc.close();
    }
}
