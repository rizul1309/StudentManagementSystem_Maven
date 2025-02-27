import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(true){
            System.out.println("\nStudent Management System");
            System.out.print("1. Add Student");
            System.out.print("2. View Student");
            System.out.print("3. Update Student");
            System.out.print("4. Delete Student");
            System.out.print("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice)
            {
                case 1: addStudent(); break;
                case 2: viewStudent(); break;
                case 3: updateStudentGrade(); break;
                case 4: DeleteSudent(); break;
                case 5: System.exit(0); break;
                default: System.out.println("Invalid choice"); break;
            }
        }
    }

    private static void addStudent(){
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Grade: ");
        double grade = scanner.nextDouble();
        students.add(new Student(id,name,grade));
        System.out.println("Student added successfully");
    }
    private static void viewStudent(){
        if(students.isEmpty()){
            System.out.println("No students found");
        }
        else{
            for(Student student : students){
                student.displayStudent();
            }
        }
    }
    private static void updateStudentGrade(){
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for(Student student : students){
            if(student.getId() == id){
                System.out.print("Enter Student Grade: ");
                student.setGrade(scanner.nextDouble());
                System.out.print("Grade updated successfully");
                return;
            }
        }
    }
    private static void DeleteSudent(){
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        students.removeIf(student -> student.getId() == id);
        System.out.println("Student deleted successfully");
    }
}
