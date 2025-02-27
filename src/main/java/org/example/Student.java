public class Student {
    private int id;
    private String name;
    private double grade;

    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    public int getId() { return id;}
    public String getName() { return name;}
    public double getGrade() { return grade;}
    public void setId(int id) { this.id = id;}
    public void setName(String name) { this.name = name;}
    public void setGrade(double grade) { this.grade = grade;}

    public void displayStudent() {
        System.out.println("ID: " + getId() + "\nName: " + getName() + "\nGrade: " + getGrade());
    }
}
