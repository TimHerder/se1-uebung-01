package org.hbrs.se1.ws26.exercises.uebung1.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Erzeugung und Initialisierung bleiben außerhalb der View. */
public final class TranslatorFactory {
    private TranslatorFactory() {
    }

    public static Translator createTranslator() {
        GermanTranslator translator = new GermanTranslator();
        translator.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        return translator;
    }
}
