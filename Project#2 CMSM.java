//University of Maryalnd Global Campus
//Cedrick Pierre 
//CMSM 215 Intermediate Programming 
//Project #2 


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int creditHours;
    private int qualityPoints;
    private static double gpaThreshold;

    public Student(String name, int creditHours, int qualityPoints) {
        this.name = name;
        this.creditHours = creditHours;
        this.qualityPoints = qualityPoints;
    }

    public double gpa() {
        return (double) qualityPoints / creditHours;
    }

    public boolean eligibleForHonorSociety() {
        return gpa() > gpaThreshold;
    }

    public static void setGpaThreshold(double threshold) {
        gpaThreshold = threshold;
        System.out.println("Honor Society GPA Threshold set to: " + gpaThreshold);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", GPA: " + gpa();
    }
}

class Undergraduate extends Student {
    private String year;

    public Undergraduate(String name, int creditHours, int qualityPoints, String year) {
        super(name, creditHours, qualityPoints);
        this.year = year;
    }

    @Override
    public boolean eligibleForHonorSociety() {
        return super.eligibleForHonorSociety() && (year.equals("Junior") || year.equals("Senior"));
    }

    @Override
    public String toString() {
        return super.toString() + ", Year: " + year;
    }
}

class Graduate extends Student {
    private String degreeSought;

    public Graduate(String name, int creditHours, int qualityPoints, String degreeSought) {
        super(name, creditHours, qualityPoints);
        this.degreeSought = degreeSought;
    }

    @Override
    public boolean eligibleForHonorSociety() {
        return super.eligibleForHonorSociety() && degreeSought.equals("Masters");
    }

    @Override
    public String toString() {
        return super.toString() + ", Degree Sought: " + degreeSought;
    }
}

public class Project2 {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        double totalGPA = 0;

        try {
            Scanner scanner = new Scanner(new File("students.txt"));
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(" ");
                String name = data[0];
                int creditHours = Integer.parseInt(data[1]);
                int qualityPoints = Integer.parseInt(data[2]);

                if (data.length == 4) {
                    String yearOrDegree = data[3];
                    students.add(new Undergraduate(name, creditHours, qualityPoints, yearOrDegree));
                } else if (data.length == 3) {
                    String degreeSought = data[3];
                    students.add(new Graduate(name, creditHours, qualityPoints, degreeSought));
                }

                totalGPA += students.get(students.size() - 1).gpa();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
            System.exit(1);
        }

        Student.setGpaThreshold((totalGPA / students.size() + 4.0) / 2);

        System.out.println("\nHonor Society Eligible Students:");
        for (Student student : students) {
            if (student.eligibleForHonorSociety()) {
                System.out.println(student);
            }
        }
    }
}
