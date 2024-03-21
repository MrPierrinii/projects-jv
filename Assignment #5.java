import java.util.Scanner;

//Name: Cedrick Pierre 
// Date: 07.21.2023

public class ConversionProgram {
    // Constants for conversion factors
    private static final double CUBIC_FEET_TO_BUSHELS = 0.803564;
    private static final double MILES_TO_KILOMETERS = 1.60934;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;

        do {
            displayMenu();
            choice = scanner.next().charAt(0);

            switch (choice) {
                case 'a':
                    convertCubicFeetToBushels();
                    break;
                case 'b':
                    convertMilesToKilometers();
                    break;
                case 'c':
                    determineGraduationWithHonorsTitle();
                    break;
                case 'd':
                    test();
                    break;
                case 'e':
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 'e');

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n");
        System.out.println("Please choose one of the following actions:");
        System.out.println("a. Convert cubic feet to U.S. bushels");
        System.out.println("b. Convert miles to kilometers");
        System.out.println("c. Determine graduation with honors title");
        System.out.println("d. Test case");
        System.out.println("e. Exit program");

        System.out.print("\nEnter your choice: ");
    }

    private static void convertCubicFeetToBushels() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the volume in cubic feet: ");
        double cubicFeet = scanner.nextDouble();
        double bushels = cubicFeet * CUBIC_FEET_TO_BUSHELS;
        System.out.println(cubicFeet + " cubic feet = " + bushels + " U.S. bushels");
    }

    private static void convertMilesToKilometers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the distance in miles: ");
        double miles = scanner.nextDouble();
        double kilometers = miles * MILES_TO_KILOMETERS;
        System.out.println(miles + " miles = " + kilometers + " kilometers");
    }

    private static void determineGraduationWithHonorsTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your GPA: ");
        double gpa = scanner.nextDouble();
        String honorsTitle;

        if (gpa >= 3.5 && gpa < 3.8) {
            honorsTitle = "Cum Laude";
        } else if (gpa >= 3.8 && gpa < 3.9) {
            honorsTitle = "Magna Cum Laude";
        } else if (gpa >= 3.9 && gpa <= 4.0) {
            honorsTitle = "Summa Cum Laude";
        } else {
            honorsTitle = "No Honors";
        }

        System.out.println("Your GPA qualifies for: " + honorsTitle);
    }

    // Test cases
    public static void test() {
        System.out.println("TEST CASES:");
        System.out.println("=================================================================");
        System.out.println("Input\t\tExpected Output\t\tActual Output\t\tStatus");
        System.out.println("=================================================================");
        testConvertCubicFeetToBushels(100, 80.3564);
        testConvertCubicFeetToBushels(50, 40.1782);
        testConvertMilesToKilometers(10, 16.0934);
        testConvertMilesToKilometers(25, 40.2335);
        testDetermineGraduationWithHonorsTitle(3.6, "Cum Laude");
        testDetermineGraduationWithHonorsTitle(3.85, "Magna Cum Laude");
        System.out.println("=================================================================");
    }

    private static void testConvertCubicFeetToBushels(double cubicFeetInput, double expectedOutput) {
        double actualOutput = cubicFeetInput * CUBIC_FEET_TO_BUSHELS;
        System.out.println(cubicFeetInput + " cubic feet = " + actualOutput + " U.S. bushels\t\t"
                + expectedOutput + " U.S. bushels\t\t" + (actualOutput == expectedOutput ? "Pass" : "Fail"));
    }

    private static void testConvertMilesToKilometers(double milesInput, double expectedOutput) {
        double actualOutput = milesInput * MILES_TO_KILOMETERS;
        System.out.println(milesInput + " miles = " + actualOutput + " kilometers\t\t"
                + expectedOutput + " kilometers\t\t" + (actualOutput == expectedOutput ? "Pass" : "Fail"));
    }

    private static void testDetermineGraduationWithHonorsTitle(double gpaInput, String expectedOutput) {
        String actualOutput;

        if (gpaInput >= 3.5 && gpaInput < 3.8) {
            actualOutput = "Cum Laude";
        } else if (gpaInput >= 3.8 && gpaInput < 3.9) {
            actualOutput = "Magna Cum Laude";
        } else if (gpaInput >= 3.9 && gpaInput <= 4.0) {
            actualOutput = "Summa Cum Laude";
        } else {
            actualOutput = "No Honors";
        }

        System.out.println("GPA: " + gpaInput + "\t\tExpected: " + expectedOutput + "\t\tActual: " + actualOutput
                + "\t\t" + (actualOutput.equals(expectedOutput) ? "Pass" : "Fail"));
    }
}
