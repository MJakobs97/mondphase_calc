package habedischnak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;

/**
 * @author MJ
 * Progamm zur Berechnung des jeweils kommenden Voll- / Neumondes
 */

public class Main {

public static JFrame mainframe;
public static JTextField datumseingabe;
public static JTextField vollmondausgabe;
public static JTextField neumondausgabe;

public static void setVollmondausgabe(String next){vollmondausgabe.setText(next);}
public static void setNeumondausgabe(String next){neumondausgabe.setText(next);}



public static void calculate_moon_dates(LocalDate now){
        if (now == null){return;}


        double synodic_month = 29.530588;

        LocalDate historic_full_moon = LocalDate.of(2000,1,21);
        //LocalDate today = LocalDate.now();
        //LocalDate today = LocalDate.of(2021,9,25);
        LocalDate today = now;
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

        setVollmondausgabe(nextFull.toString());
        setNeumondausgabe(nextNew.toString());

}

public static void createGUI(){
    mainframe = new JFrame("Moon Calculations");
    mainframe.setSize(500,500);


    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(0,1));

    JLabel labelheute = new JLabel("Heutiges Datum");
    //labelheute.setLocation(50,20);
    labelheute.setVisible(true);
    panel.add(labelheute);

    datumseingabe = new JTextField("yyyy-mm-dd");
    datumseingabe.setEditable(true);
    //datumseingabe.setLocation(100,20);
    datumseingabe.setVisible(true);
    panel.add(datumseingabe );


    JLabel labelnextfull = new JLabel("Nächster Vollmond");
    //labelnextfull.setLocation(50,50);
    labelnextfull.setVisible(true);
    panel.add(labelnextfull );


    vollmondausgabe = new JTextField("");
    vollmondausgabe.setEditable(false);
    //vollmondausgabe.setLocation(200,50);
    vollmondausgabe.setVisible(true);
    panel.add(vollmondausgabe );

    JLabel labelnextnew = new JLabel("Nächster Neumond");
    //labelnextfull.setLocation(200,150);
    labelnextfull.setVisible(true);
    panel.add(labelnextnew );


    neumondausgabe = new JTextField("");
    neumondausgabe.setEditable(false);
    //neumondausgabe.setLocation(200,50);
    neumondausgabe.setVisible(true);
    panel.add(neumondausgabe );





    JButton calc = new JButton("Berechne");
    calc.setEnabled(true);
    //calc.setLocation(150,300);
    calc.setVisible(true);
    calc.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent e) {
                                   calculate_moon_dates(readToday());
                               }
                           });
    panel.add(calc);


    panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

    mainframe.getContentPane().add(panel);
    mainframe.pack();
    mainframe.show();

}





public static LocalDate readToday(){

    Component[] components = mainframe.getContentPane().getComponents();
    String today = datumseingabe.getText();

    /*for (Component c:components) {
        if (c instanceof JTextField && ((JTextField) c).isEditable()){today = ((JTextField) c).getText();}
    }*/

    if (today.equalsIgnoreCase("yyyy-mm-dd") || today.matches("^[y]{1,4}[-][m]{0,2}[-][d]{0,4}$") || today.isEmpty() || today.length()<8 || today.length()>10){return null;}

    String[] fractured = today.split("-");

    LocalDate dateOfToday = LocalDate.of(Integer.parseInt(fractured[0]), Integer.parseInt(fractured[1]), Integer.parseInt(fractured[2]));

    return dateOfToday;
}

    public static void main(String[] args) throws IOException {
	// write your code here
    //calculate_moon_dates();

    createGUI();



    }
}
