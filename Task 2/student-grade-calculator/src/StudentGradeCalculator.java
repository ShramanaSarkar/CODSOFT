import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking user input for marks
        System.out.println("Enter marks obtained out of 100:");

        System.out.print("Physics: ");
        int physics = scanner.nextInt();

        System.out.print("Chemistry: ");
        int chemistry = scanner.nextInt();

        System.out.print("Maths: ");
        int maths = scanner.nextInt();

        System.out.print("English: ");
        int english = scanner.nextInt();

        System.out.print("Computer Science: ");
        int computerScience = scanner.nextInt();

        // Calculating total marks
        int totalMarks = physics + chemistry + maths + english + computerScience;

        // Calculating average percentage
        double averagePercentage = totalMarks / 5.0;

        // Assigning grade based on percentage
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Displaying results
        System.out.println("\n---- Result ----");
        System.out.println("Total Marks: " + totalMarks + "/500");
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}