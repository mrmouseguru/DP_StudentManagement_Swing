package business;


public class SoftwareStudent extends Student {
    private double javaScore;
    private double htmlScore;
    private double cssScore;

    public SoftwareStudent(String id, String name, String birthDate,
                           double javaScore, double htmlScore, double cssScore) {
        super(id, name, birthDate);
        this.major = "Kỹ thuật phần mềm";
        this.javaScore = javaScore;
        this.htmlScore = htmlScore;
        this.cssScore = cssScore;
    }

    @Override
    public double calculateGPA() {
        return (2 * javaScore + htmlScore + cssScore) / 4;
    }
    
    public double getJavaScore() { return javaScore; }
    public double getHtmlScore() { return htmlScore; }
    public double getCssScore() { return cssScore; }

}