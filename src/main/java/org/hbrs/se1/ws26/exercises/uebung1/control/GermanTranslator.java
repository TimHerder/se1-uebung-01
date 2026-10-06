package org.hbrs.se1.ws26.exercises.uebung1.control;

public class GermanTranslator implements Translator {

	public String date = null;

	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	@Override
	public String translateNumber(int number) {
		// Einzige Ausnahme vom Bereich 1..100 gemäß Zusatzanforderung.
		if (number == Integer.MAX_VALUE) {
			return "zwei Milliarden einhundertsiebenundvierzig Millionen "
					+ "vierhundertdreiundachtzigtausendsechshundertsiebenundvierzig";
		}
		if (number < 1 || number > 100) {
			return "Übersetzung der Zahl " + number + " nicht möglich (" + Translator.version + ")";
		}
		if (number == 100) {
			return "einhundert";
		}

		String[] small = {"", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben",
				"acht", "neun", "zehn", "elf", "zwölf", "dreizehn", "vierzehn", "fünfzehn",
				"sechzehn", "siebzehn", "achtzehn", "neunzehn"};
		if (number < 20) {
			return small[number];
		}
		String[] tens = {"", "", "zwanzig", "dreißig", "vierzig", "fünfzig", "sechzig",
				"siebzig", "achtzig", "neunzig"};
		int unit = number % 10;
		if (unit == 0) {
			return tens[number / 10];
		}
		String prefix = unit == 1 ? "ein" : small[unit];
		return prefix + "und" + tens[number / 10];
	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	void printInfo(){
		System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: dd.MM.yyyy (Beispiel: "20.08.2026"))
	 * Das Datum sollte system-intern durch eine Factory-Klasse gesetzt werden und nicht von externen View-Klassen
	 * Technisch sollte einfach das "heutige" Datum gesetzt werden.
	 */
	public void setDate( String date ) {
		this.date = date;
	}

	/**
	 * Auslesen des gesetzten Datums
	 * @return
	 */
	public String getDate() {
		return date;
	}
}
