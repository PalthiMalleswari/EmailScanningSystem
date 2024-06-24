package com.malleswari;

public class Keyword {
	
	private int id;
    private String keyword;
    private double weight;

    public Keyword(int id, String keyword, double weight) {
        this.id = id;
        this.keyword = keyword;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Keyword{" +
                "id=" + id +
                ", keyword='" + keyword + '\'' +
                ", weight=" + weight +
                '}';
    }

	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	public String getKeyword() {
		return this.keyword;
	}
	public double getWeight() {
		return this.weight;
	}

}
