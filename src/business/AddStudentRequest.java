package business;

public class AddStudentRequest {
    public String id;
    public String name;
    public String birth;
    public String major;
    public String javaStr;
    public String htmlStr;
    public String cssStr;
    public String marketingStr;
    public String salesStr;

    public AddStudentRequest() {}

    public AddStudentRequest(String id, String name, String birth, String major,
                             String javaStr, String htmlStr, String cssStr,
                             String marketingStr, String salesStr) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.major = major;
        this.javaStr = javaStr;
        this.htmlStr = htmlStr;
        this.cssStr = cssStr;
        this.marketingStr = marketingStr;
        this.salesStr = salesStr;
    }
}
