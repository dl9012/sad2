import java.util.Scanner;

public class Converter {

    private static final String CELSIUS_TO_FAHRENHEIT_COMMAND = "CTF";
    private static final String FAHRENHEIT_TO_CELSIUS_COMMAND = "FTC";
    private static final int WRONG_COMMAND_ERROR_CODE = -1;

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        printMenu();
        String command = readCommand();
        handleCommand(command);
        input.close();
    }

    private static void printMenu() {
        System.out.println("Commands:");
        System.out.println("CTF: Celsius to Fahrenheit");
        System.out.println("FTC: Fahrenheit to Celsius");
    }

    private static String readCommand() {
        System.out.print("> ");
        String command = input.nextLine().trim().toUpperCase();
        return command;
    }

    private static void handleCommand(String command) {
        switch (command) {
            case CELSIUS_TO_FAHRENHEIT_COMMAND:
                convertCelsiusToFahrenheit();
                break;
            case FAHRENHEIT_TO_CELSIUS_COMMAND:
                convertFahrenheitToCelsius();
                break;
            default:
                System.err.println("Wrong command");
                System.exit(WRONG_COMMAND_ERROR_CODE);
        }
    }

    private static void convertCelsiusToFahrenheit() {
        double c = readDegrees("Celsius");
        double f = celsiusToFahrenheit(c);
        printResult(c, 'C', f, 'F');
    }

    static double celsiusToFahrenheit(double c) {
        return 9 / 5 * c + 32;
    }

    private static void convertFahrenheitToCelsius() {
        double f = readDegrees("Fahrenheit");
        double c = fahrenheitToCelsius(f);
        printResult(f, 'F', c, 'C');
    }

    static double fahrenheitToCelsius(double f) {
        return f - 32 * 5 / 9;
    }

    private static double readDegrees(String unit) {
        System.out.println("Enter degrees " + unit);
        System.out.print("> ");
        return input.nextDouble();
    }

    private static void printResult(double original, char originalUnit, double result, char resultUnit) {
        System.out.println(formatResult(original, originalUnit, result, resultUnit));
    }

    static String formatResult(double original, char originalUnit, double result, char resultUnit) {
        return "%.1f°%c is %.1f°%c".formatted(original, originalUnit, result, resultUnit);
    }

}