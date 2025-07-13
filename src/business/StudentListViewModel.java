package business;

public class StudentListViewModel {
    public String id;
    public String name;
    public String birth;
    public String major;
    public Double gpa;
    public String academicRank;

    public StudentListViewModel(String id, String name, String birth, String major, Double gpa, String academicRank) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.major = major;
        this.gpa = gpa;
        this.academicRank = academicRank;
    }
}
