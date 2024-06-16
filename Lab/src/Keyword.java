
public class Keyword {
    private int id;
    private String keyword;
    private double weight;

    public Keyword(int id, String keyword, double weight) {
        this.id = id;
        this.keyword = keyword;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }

    public double getWeight() {
        return weight;
    }
}
