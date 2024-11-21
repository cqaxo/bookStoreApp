package application;
	
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		// Start at login view
        Login loginScreen = new Login();
        loginScreen.start(primaryStage); // Pass the primary stage to the Login class
        
		
		// Path to the file
        String bookPath = "src/books.txt"; // Adjust this path as needed
        String userPath = "src/users.txt"; // Adjust this path as needed
        String transactionPath = "src/transactions.txt"; // Adjust this path as needed

        // List to store BookInfo objects
        List<bookInfo> books = new ArrayList<>();
        
        // List for Users
        List<bookInfo> users = new ArrayList<>();
        
        // list for transactions
        List<bookInfo> transactions = new ArrayList<>();
        
        
        try (BufferedReader reader = new BufferedReader(new FileReader(bookPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String name = line; // Reads first line for book name
                if ((line = reader.readLine()) != null) {
                    // Second line is book details: category condition, price
                    String[] details = line.split(" ");
                    char category = details[0].charAt(0);
                    int condition = Integer.parseInt(details[1]);
                    double price = Double.parseDouble(details[2]);
                    books.add(new bookInfo(name, category, condition, price));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        /*
         * Commented out to implement login functionality
         * If needed, you can uncomment it for testing purposes
         * 
		Group root = new Group();
		Scene scene = new Scene(root);
		
		primaryStage.setWidth(800);
		primaryStage.setHeight(500);
		
		//Buttons
		Button sellView = new Button("Seller's View");
		sellView.setPrefSize(80,40);
		sellView.setStyle("-fx-font-size: 9px;");
		
		Button buyView = new Button("Buyer's View");
		buyView.setPrefSize(80,40);
		buyView.setStyle("-fx-font-size: 9px;");
		
		Button adminView = new Button("Administrator's View");
		buyView.setPrefSize(80,40);
		buyView.setStyle("-fx-font-size: 9px;");
		
		//HBoxes
		HBox layout = new HBox(50);
		layout.getChildren().addAll(buyView, sellView, adminView);
		layout.setLayoutX(150);
		layout.setLayoutY(320);
		
		root.getChildren().addAll(layout);
		
		// Set button action to switch to sellerView
        sellView.setOnAction(e -> {
            sellerView sellerScreen = new sellerView(primaryStage); // Pass stage to sellerView
            sellerScreen.showSellerView();
        });
        
        //this one goes to buyer view
        buyView.setOnAction(e -> {
            buyerView buyerScreen = new buyerView(primaryStage); // Pass stage to sellerView
            buyerScreen.showBuyerView();
        });
        //This one goes to the admin view
        adminView.setOnAction(e -> {
            adminView adminScreen = new adminView(primaryStage); // Pass stage to sellerView
            adminScreen.showAdminView();
        });
		
		primaryStage.setScene(scene);
		primaryStage.show();
	
	*/
        
	}
	
	
        
	public static void main(String[] args) {
		launch(args);
	}
}
