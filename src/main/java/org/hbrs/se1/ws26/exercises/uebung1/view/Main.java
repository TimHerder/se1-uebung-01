package org.hbrs.se1.ws26.exercises.uebung1.view;

/** Separater Einstiegspunkt: Der Client selbst erzeugt keine Objekte. */
public class Main {
    public static void main(String[] args) {
        int number = args.length == 0 ? 67 : Integer.parseInt(args[0]);
        new Client().display(number);
    }
}
