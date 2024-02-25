package com.tikalabs.core.string.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumericConverter {

	/**
	 * Konvertiert einen String in einen Double-Wert unter Berücksichtigung des
	 * Lokalisierungskontextes. Gibt 0.00 zurück, wenn der Eingabestring null ist.
	 *
	 * @param value  der zu konvertierende String
	 * @param locale die Lokalisierung, die für die Formatierung des Strings
	 *               verwendet wird
	 * @return den konvertierten Double-Wert oder 0.00, wenn der Eingabestring null
	 *         ist
	 */

	private static final Logger logger = LoggerFactory.getLogger(NumericConverter.class);

	public static double parseDouble(String value, Locale locale) throws ParseException {
		if ((value == null) || (value.isBlank())) {
			return 0.00;
		}

		NumberFormat format = NumberFormat.getInstance(locale);
		try {
			Number number = format.parse(value);
			return number.doubleValue();
		} catch (ParseException e) {
			// Loggen des Fehlers und Rückgabe von 0.00 als Fallback
			logger.error("Fehler beim Parsen des Werts: " + value + ".");
			throw e;
		}
	}

	/**
	 * Konvertiert einen String in einen Double-Wert unter Verwendung der
	 * Standard-Lokalisierung. Gibt 0.00 zurück, wenn der Eingabestring null ist.
	 *
	 * @param value der zu konvertierende String
	 * @return den konvertierten Double-Wert oder 0.00, wenn der Eingabestring null
	 *         ist
	 * @throws ParseException
	 */
	public static double parseDouble(String value) throws ParseException {
		return parseDouble(value, Locale.getDefault());
	}
}
