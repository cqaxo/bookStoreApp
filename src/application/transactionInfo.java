package application;

public class transactionInfo {
	public String name;
    public String bName;
    public String condition;
    public String price;

    // Constructor
    public transactionInfo(String name, String bName, String condition, String price) {
        this.name = name;
        this.bName = bName;
        this.condition = condition;
        this.price = price;
    }
}
