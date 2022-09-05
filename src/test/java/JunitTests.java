import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

// ctf and ftc used as shorthand to avoid having to write the full names everywhere.
public class JunitTests {

    private static final double DEFAULT_DOUBLE_DELTA = 0.0001;

    @Test
    void ctf_positiveTemperature_noDecimals() {
        double actual = Converter.celsiusToFahrenheit(10);
        assertEquals(50.0, actual, DEFAULT_DOUBLE_DELTA);
    }

    @Test
    void ctf_positiveTemperature_decimals() {
        double actual = Converter.celsiusToFahrenheit(26.3);
        assertEquals(79.34, actual, DEFAULT_DOUBLE_DELTA);
    }

    @Test
    void ctf_negativeTemperature() {
        double actual = Converter.celsiusToFahrenheit(-10);
        assertEquals(14.0, actual, DEFAULT_DOUBLE_DELTA);
    }

    @Test
    void ctf_zeroDegrees() {
        double actual = Converter.celsiusToFahrenheit(0);
        assertEquals(32.0, actual, DEFAULT_DOUBLE_DELTA);
    }

    @Test
    void ftc_positiveTemperature_noDecimals() {
        double actual = Converter.fahrenheitToCelsius(50);
        assertEquals(10.0, actual, DEFAULT_DOUBLE_DELTA);
    }

    @Test
    void ftc_positiveTemperature_decimals() {
        double actual = Converter.fahrenheitToCelsius(79);
        assertEquals(26.1111, actual, DEFAULT_DOUBLE_DELTA);
    }

    @Test
    void ftc_negativeTemperature() {
        double actual = Converter.fahrenheitToCelsius(-10);
        assertEquals(-23.3333, actual, DEFAULT_DOUBLE_DELTA);
    }

    @Test
    void ftc_zeroDegrees() {
        double actual = Converter.fahrenheitToCelsius(0);
        assertEquals(-17.7778, actual, DEFAULT_DOUBLE_DELTA);
    }

    @Test
    void resultFormat_noDecimals() {
        String actual = Converter.formatResult(1.0, 'A', 2.0, 'B');
        assertEquals("1.0°A is 2.0°B", actual);
    }

    @Test
    void resultFormat_oneDecimal() {
        String actual = Converter.formatResult(1.2, 'A', 3.4, 'B');
        assertEquals("1.2°A is 3.4°B", actual);
    }

    @Test
    void resultFormat_moreThanOneDecimalRoundedUp() {
        String actual = Converter.formatResult(1.25, 'A', 3.49, 'B');
        assertEquals("1.3°A is 3.5°B", actual);
    }

    @Test
    void resultFormat_moreThanOneDecimalRoundedDown() {
        String actual = Converter.formatResult(1.21, 'A', 3.449, 'B');
        assertEquals("1.2°A is 3.4°B", actual);
    }

    @ParameterizedTest()
    @CsvSource(value = { "se,SV,'0,0°X is 0,0°X'", "en,GB,0.0°X is 0.0°X" })
    void resultFormat_followsLocaleSettings(String language, String country, String expected) {
        Locale original = Locale.getDefault();
        try {
            Locale.setDefault(new Locale(language, country));
            String actual = Converter.formatResult(0, 'X', 0, 'X');
            assertEquals(expected, actual);
        } finally {
            Locale.setDefault(original);
        }
    }

}