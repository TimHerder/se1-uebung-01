package se1.ws26.tests.uebung1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import org.hbrs.se1.ws26.exercises.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws26.exercises.uebung1.control.Translator;
import org.hbrs.se1.ws26.exercises.uebung1.control.TranslatorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/** Blackbox-Tests: Erwartungen stammen aus der Spezifikation, nicht dem Algorithmus. */
public class GermanTranslatorTest {
    private final Translator translator = TranslatorFactory.createTranslator();

    @Test
    void lowerBoundaryOne() { assertEquals("eins", translator.translateNumber(1)); }

    @Test
    void singleDigit() { assertEquals("fünf", translator.translateNumber(5)); }

    @Test
    void ten() { assertEquals("zehn", translator.translateNumber(10)); }

    @Test
    void irregularElevenAndTwelve() {
        assertEquals("elf", translator.translateNumber(11));
        assertEquals("zwölf", translator.translateNumber(12));
    }

    @Test
    void teensAndShortenedStems() {
        assertEquals("dreizehn", translator.translateNumber(13));
        assertEquals("sechzehn", translator.translateNumber(16));
        assertEquals("siebzehn", translator.translateNumber(17));
        assertEquals("neunzehn", translator.translateNumber(19));
    }

    @Test
    void twenty() { assertEquals("zwanzig", translator.translateNumber(20)); }

    @Test
    void oneInCompound() { assertEquals("einundzwanzig", translator.translateNumber(21)); }

    @Test
    void thirty() { assertEquals("dreißig", translator.translateNumber(30)); }

    @Test
    void shortenedTens() {
        assertEquals("sechzig", translator.translateNumber(60));
        assertEquals("siebzig", translator.translateNumber(70));
    }

    @Test
    void ordinaryCompound() { assertEquals("siebenundsechzig", translator.translateNumber(67)); }

    @Test
    void upperBoundaryNinetyNine() { assertEquals("neunundneunzig", translator.translateNumber(99)); }

    @Test
    void upperBoundaryHundred() { assertEquals("einhundert", translator.translateNumber(100)); }

    @ParameterizedTest(name = "Ungültige Zahl {0}")
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 0, 101, 1000, Integer.MAX_VALUE - 1})
    void invalidNumbers(int number) {
        assertEquals("Übersetzung der Zahl " + number + " nicht möglich (1.0)",
                translator.translateNumber(number));
    }

    @Test
    void interfaceVersion() { assertEquals(1.0, Translator.version); }

    @Test
    void maximumIntegerIsExplicitException() {
        assertEquals("zwei Milliarden einhundertsiebenundvierzig Millionen "
                + "vierhundertdreiundachtzigtausendsechshundertsiebenundvierzig",
                translator.translateNumber(Integer.MAX_VALUE));
    }

    @Test
    void factorySetsCurrentDate() {
        // Vorher/nachher erlaubt den legitimen Tageswechsel während der Erzeugung.
        LocalDate before = LocalDate.now();
        Translator created = TranslatorFactory.createTranslator();
        LocalDate after = LocalDate.now();
        GermanTranslator german = assertInstanceOf(GermanTranslator.class, created);
        assertNotNull(german.getDate());
        assertTrue(german.getDate().matches("\\d{2}\\.\\d{2}\\.\\d{4}"));
        LocalDate date = LocalDate.parse(german.getDate(),
                DateTimeFormatter.ofPattern("dd.MM.uuuu").withResolverStyle(ResolverStyle.STRICT));
        assertTrue(date.equals(before) || date.equals(after));
    }

    @Test
    void factoryCreatesIndependentObjects() {
        assertNotSame(TranslatorFactory.createTranslator(), TranslatorFactory.createTranslator());
    }

    @ParameterizedTest(name = "Vollständiger Wertebereich: {0} → {1}")
    @CsvFileSource(resources = "/german-numbers.csv", encoding = "UTF-8")
    void allNumbersAgainstIndependentOracle(int number, String expected) {
        assertEquals(expected, translator.translateNumber(number));
    }
}
