package habedischnak;

import java.time.Duration;
import java.time.LocalDate;

public class Main {




    public static void main(String[] args) {
	// write your code here
    double synodic_month = 29.530588;

	LocalDate historic_full_moon = LocalDate.of(2000,1,21);
    LocalDate today = LocalDate.now();

    double elapsed = Duration.between(historic_full_moon.atStartOfDay(), today.atStartOfDay()).toDays();
    double elapsed_cycles = elapsed/synodic_month;

    double elapsed_nachkomma = (elapsed_cycles-(elapsed_cycles/1));
    double until_next_full = (1 - elapsed_nachkomma)*synodic_month;



    System.out.println(until_next_full);






    }
}
