package business;

public class EconomicsStudent extends Student {
    private double marketingScore;
    private double salesScore;

    public EconomicsStudent(String id, String name, String birthDate,
                             double marketingScore, double salesScore) {
        super(id, name, birthDate);
        this.major = "Kinh tế";
        this.marketingScore = marketingScore;
        this.salesScore = salesScore;
    }

    @Override
    public double calculateGPA() {
        return (2 * marketingScore + salesScore) / 3;
    }
    
    public double getMarketingScore() { return marketingScore; }
    public double getSalesScore() { return salesScore; }
}