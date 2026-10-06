package org.hbrs.se1.ws26.exercises.uebung1.view;

import org.hbrs.se1.ws26.exercises.uebung1.control.Translator;
import org.hbrs.se1.ws26.exercises.uebung1.control.TranslatorFactory;

public class Client {
    /**
     * Methode zur Ausgabe einer Zahl auf der Console
     * (auch bezeichnet als CLI, Terminal)
     * Verwendung des Design Pattern: Simple Factory (Fabrik).
     * Problem: Die View soll keine konkrete Übersetzerklasse erzeugen oder kennen.
     * Lösung: Die Fabrik liefert ein initialisiertes Objekt als Translator.
     */
    void display(int aNumber) {
        // In dieser Methode soll die Methode translateNumber
        // mit dem übergegebenen Wert der Variable aNumber
        // aufgerufen werden.
        // Strenge Implementierung (nur) gegen das Interface Translator gewuenscht!
        Translator translator = TranslatorFactory.createTranslator();
        System.out.println("Das Ergebnis der Berechnung: " + translator.translateNumber(aNumber));
    }
}
