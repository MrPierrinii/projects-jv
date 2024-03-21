// Author: Cedrick Pierre 
//Date: 07.11.2023
//This program calculates a gamer's total XP score with bonuses per level.
//It prompts the user to input the gamer's information, such as name, XP scores, and engagement score.
//The total XP score is calculated based on the provided formula.
//The program outputs the gamer's information and the calculated total XP score.
//It also allows the user to calculate total XP for another gamer or exit the program.

import java.util.Scanner;

public class GamerXP {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean continueCalculating = true;
        String choice = "no";
        String name = "";

        while (continueCalculating) {
            System.out.print("Enter gamer's name (or 'exit' to quit): ");
            name = scanner.next();

            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            int xp1 = readXP(scanner, "Level 1");
            int xp2 = readXP(scanner, "Level 2");
            int xp3 = readXP(scanner, "Level 3");
            int engagementScore = readEngagementScore(scanner);

            double totalXP = calculateTotalXP(xp1, xp2, xp3, engagementScore);

            System.out.println("\nGamer's Information:");
            System.out.println("Name: " + name);
            System.out.println("Level 1 XP: " + xp1);
            System.out.println("Level 2 XP: " + xp2);
            System.out.println("Level 3 XP: " + xp3);
            System.out.println("Engagement Score: " + engagementScore);
            System.out.println("Total XP with Bonuses: " + totalXP);

            System.out.print("Do you want to calculate total XP for another gamer? (yes/no): ");
            choice = scanner.next();

            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                continueCalculating = true;
            } else {
                continueCalculating = false;
            }

        }
        scanner.close();

        System.out.println("Exiting the program.");

    }

    /**
     * Reads a valid XP score input from the user within the specified range and
     * increments.
     * 
     * @param scanner Scanner object to read input
     * @param level   Level number for the XP score
     * @return Valid XP score input
     */
    private static int readXP(Scanner scanner, String level) {
        int xp;
        do {
            System.out.print("Enter " + level + " XP (10-100 in increments of 5): ");
            xp = scanner.nextInt();
        } while (xp < 10 || xp > 100 || xp % 5 != 0);

        return xp;
    }

    /**
     * Reads a valid engagement score input from the user within the specified range
     * and increments.
     * 
     * @param scanner Scanner object to read input
     * @return Valid engagement score input
     */
    private static int readEngagementScore(Scanner scanner) {
        int engagementScore;
        do {
            System.out.print("Enter Engagement Score (10-100 in increments of 5): ");
            engagementScore = scanner.nextInt();
        } while (engagementScore < 10 || engagementScore > 100 || engagementScore % 5 != 0);

        return engagementScore;
    }

    /**
     * Calculates the total XP score with bonuses based on the provided XP scores
     * and engagement score.
     * 
     * @param xp1             Level 1 XP score
     * @param xp2             Level 2 XP score
     * @param xp3             Level 3 XP score
     * @param engagementScore Engagement score
     * @return Total XP score with bonuses
     */
    private static double calculateTotalXP(int xp1, int xp2, int xp3, int engagementScore) {
        return xp1 + xp1 * 0.20 + xp2 + xp2 * 0.30 + xp3 + xp3 * 0.50 + engagementScore + engagementScore * 0.60;
    }
}
