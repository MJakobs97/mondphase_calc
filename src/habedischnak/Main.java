package habedischnak;

import javax.swing.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;

/**
 * @author MJ
 * Progamm zur Berechnung des jeweils kommenden Voll- / Neumondes
 */

public class Main {
    public static void calculate_moon_dates(){
        double synodic_month = 29.530588;

        LocalDate historic_full_moon = LocalDate.of(2000,1,21);
        LocalDate today = LocalDate.now();
        //LocalDate today = LocalDate.of(2021,9,25);
        LocalDate nextFull, nextNew;

        /*Get time between today and historic full moon & calculate cycles */
        double elapsed = Duration.between(historic_full_moon.atStartOfDay(), today.atStartOfDay()).toDays();
        double elapsed_cycles = elapsed/synodic_month;
        double elapsed_nachkomma = (elapsed_cycles-((int) elapsed_cycles));

        /*multiply percentage remainder times synodic month to get remaining days to the next full moon*/
        double until_next_full = (1 - elapsed_nachkomma)*synodic_month;
        nextFull = today.plusDays((long)until_next_full);

        System.out.println("Next full moon is on "+nextFull);

        /*multiply percentage remainder times synodic month to get remaining days to the next full moon*/
        double until_next_new = (0.5 - elapsed_nachkomma)*synodic_month +1;
        nextNew = today.plusDays((long)until_next_new);

        System.out.println("Next new moon is on "+nextNew);
}

    public static void main(String[] args) throws IOException {
	// write your code here
    //calculate_moon_dates();

    /*Bare window*/
    JFrame dateFrame = new JFrame("Next Moon");
    //dateFrame.setLayout(null);




    /*Labels*/
    JLabel today = new JLabel("Heutiges Datum");
    today.setLocation(50,50);

    dateFrame.getContentPane().add(today);
    dateFrame.setSize(640,480);
    dateFrame.setVisible(true);
    }
}
