package business;


public abstract class Student {
    protected String id;
    protected String name;
    protected String birthDate;
    protected String major;

    public Student(String id, String name, String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public abstract double calculateGPA();

    public String classifyAcademic() {
        double gpa = calculateGPA();
        if (gpa < 5) return "Yếu";
        else if (gpa < 6.5) return "Trung bình";
        else if (gpa < 7.5) return "Khá";
        else if (gpa < 9) return "Giỏi";
        else return "Xuất sắc";
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getBirthDate() { return birthDate; }
    public String getMajor() { return major; }
}
