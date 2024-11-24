package application;

public class bookInfo {
	
	public String name;
    public char category;
    public int condition;
    public double price;
    
 // Static field to count the number of books
    private static int bookCount = 0;

    // Constructor
    public bookInfo(String name, char category, int condition, double price) {
        this.name = name;
        this.category = category;
        this.condition = condition;
        this.price = price;
        
        bookCount++;
    }
}
