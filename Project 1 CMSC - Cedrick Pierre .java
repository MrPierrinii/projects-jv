// Project 1
// Cedrick Pierre 
// CMSC 215 6381 Intermediate Programing 
// 1.23.2024

class Height {
    private int feet;
    private int inches;

    public Height(int feet, int inches) {
        this.feet = feet;
        this.inches = inches;
    }

    public int toInches() {
        return feet * 12 + inches;
    }

    public String toString() {
        return feet + "'" + normalizeInches(inches) + "\"";
    }

    private String normalizeInches(int inches) {
        if (inches >= 12) {
            feet++;
            inches -= 12;
        }

        return String.valueOf(inches);
    }
}

class Player {
    private String name;
    private Height height;
    private int age;

    public Player(String name, Height height, int age) {
        this.name = name;
        this.height = height;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Height getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return String.format("%s, %s, %d", name, height, age);
    }
}

class Project1 {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        int totalAge = 0;

        System.out.println("Enter the information for each player.");
        while (true) {
            String name = promptString("Enter the player's name: ");
            if (name.equals("")) {
                break;
            }

            int feet = promptInt("Enter the player's feet: ");
            int inches = promptInt("Enter the player's inches: ");

            Height height = new Height(feet, inches);

            int age = promptInt("Enter the player's age: ");

            players.add(new Player(name, height, age));

            totalAge += age;
        }

        double averageAge = (double) totalAge / players.size();
        System.out.println("The average age of all players is " + averageAge);

        System.out.println("The tallest player whose age is less than or equal to the average age is:");
        Collections.sort(players, (a, b) -> a.getHeight().toInches() - b.getHeight().toInches());

        for (Player player : players) {
            if (player.getAge() <= averageAge) {
                System.out.println(player);
                break;
            }
        }
    }

    private static String promptString(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static int promptInt(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
