import java.util.Scanner;

/**
 * This program is a conversion tool and GPA honors determiner.
 * 
 * @author Cedrick Pierre 
 * @assignment#5 
 * @since 2023-08-04
 */
public class ConversionTool {

    private static final double CUBIC_FEET_TO_BUSHELS_FACTOR = 0.803564;
    private static final double MILES_TO_KILOMETERS_FACTOR = 1.60934;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userOption;

        // Prompt the user until they exit
        do {
            displayMenu();
            userOption = scanner.nextInt();

            switch (userOption) {
                case 1:
                    performCubicFeetToBushelsConversion(scanner);
                    break;
                case 2:
                    performMilesToKilometersConversion(scanner);
                    break;
                case 3:
                    determineGraduationHonors(scanner);
                    break;
                case 4:
                    System.out.println("Exiting program");
                    break;
                default:
                    System.out.println("Invalid option. Please enter a valid one.");
                    break;
            }
        } while (userOption != 4);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Please choose an action:");
        System.out.println("1. Convert cubic feet to U.S. bushels");
        System.out.println("2. Convert miles to kilometers");
        System.out.println("3. Determine graduation with honors title");
        System.out.println("4. Exit program");
        System.out.print("Enter option: ");
    }

    private static void performCubicFeetToBushelsConversion(Scanner scanner) {
        System.out.print("Enter cubic feet: ");
        double cubicFeet = scanner.nextDouble();
        double bushels = convertCubicFeetToBushels(cubicFeet);
        System.out.println(cubicFeet + " cubic feet = " + bushels + " U.S. bushels");
    }

    private static double convertCubicFeetToBushels(double cubicFeet) {
        return cubicFeet * CUBIC_FEET_TO_BUSHELS_FACTOR;
    }

    private static void performMilesToKilometersConversion(Scanner scanner) {
        System.out.print("Enter miles: ");
        double miles = scanner.nextDouble();
        double kilometers = convertMilesToKilometers(miles);
        System.out.println(miles + " miles = " + kilometers + " kilometers");
    }

    private static double convertMilesToKilometers(double miles) {
        return miles * MILES_TO_KILOMETERS_FACTOR;
    }

    private static void determineGraduationHonors(Scanner scanner) {
        System.out.print("Enter GPA: ");
        double gpa = scanner.nextDouble();
        String honorsTitle = getHonorsTitle(gpa);
        System.out.println("Your honors title is: " + honorsTitle);
    }

    private static String getHonorsTitle(double gpa) {
        if (gpa < 3.5) {
            return "No Honors";
        } else if (gpa <= 3.7) {
            return "Cum Laude";
        } else if (gpa <= 3.9) {
            return "Magna Cum Laude";
        } else {
            return "Summa Cum Laude";
        }
    }
}
