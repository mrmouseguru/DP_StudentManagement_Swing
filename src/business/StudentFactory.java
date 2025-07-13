package business;

public class StudentFactory {
    public static Student create(AddStudentRequest req) throws Exception {
        if (req.major.equals("Kỹ thuật phần mềm")) {
            return new SoftwareStudent(req.id, req.name, req.birth,
                AddStudentService.parseScore(req.javaStr, "Java"),
                AddStudentService.parseScore(req.htmlStr, "HTML"),
                AddStudentService.parseScore(req.cssStr, "CSS"));
        } else if (req.major.equals("Kinh tế")) {
            return new EconomicsStudent(req.id, req.name, req.birth,
                AddStudentService.parseScore(req.marketingStr, "Marketing"),
                AddStudentService.parseScore(req.salesStr, "Sales"));
        } else {
            throw new Exception("Ngành học không hợp lệ");
        }
    }
}
