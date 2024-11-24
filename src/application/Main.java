package application;
	
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.scene.paint.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		// Start at login view
        Login loginScreen = new Login();
        loginScreen.start(primaryStage); // Pass the primary stage to the Login class
		
		// Path to the file
        String bookPath = "books.txt"; // Adjust this path as needed
        String userPath = "users.txt"; // Adjust this path as needed
        String transactionPath = "transactions.txt"; // Adjust this path as needed

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
		
        /* Uncomment to test different views

        // Main Stage
        primaryStage.setWidth(800);
        primaryStage.setHeight(500);

        // Main container
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle(
                        "-fx-background-color: #FFC627;" +
                        "-fx-border-color: #8C1D40;" +
                        "-fx-border-width: 5;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;"
        );
        root.setAlignment(Pos.CENTER);

        // Title
        Text title = new Text("SunDevil Bookstore");
        title.setFont(Font.font("Times New Roman", 50));
        title.setFill(Color.web("#8C1D40"));
        StackPane header = new StackPane(title);
        header.setStyle("-fx-background-color: white; -fx-padding: 10;");
        root.getChildren().add(header);

        // Button layout
        HBox buttonLayout = new HBox(30);
        buttonLayout.setAlignment(Pos.CENTER);

		// Buttons
		Button sellView = new Button("Seller's View");
		sellView.setStyle("-fx-background-color: #8C1D40; -fx-text-fill: #FFC627; -fx-font-weight: bold;");

		Button buyView = new Button("Buyer's View");
		buyView.setStyle("-fx-background-color: #8C1D40; -fx-text-fill: #FFC627; -fx-font-weight: bold;");

		Button adminView = new Button("Administrator's View");
		adminView.setStyle("-fx-background-color: #8C1D40; -fx-text-fill: #FFC627; -fx-font-weight: bold;");

        buttonLayout.getChildren().addAll(sellView, buyView, adminView);
        root.getChildren().add(buttonLayout);

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

        // Set Main scene to front
        Scene mainScene = new Scene(root, 800, 500);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("SunDevil Bookstore - Main");
        primaryStage.show();

        */
	}


        
	public static void main(String[] args) {
		launch(args);
	}
}
