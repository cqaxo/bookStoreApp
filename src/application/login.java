package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.geometry.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setWidth(800);
        primaryStage.setHeight(500);

        // Create UI elements
        VBox layout = new VBox(20); // Main layout container
        layout.setPadding(new Insets(10));
        layout.setStyle(
                "-fx-background-color: #FFC627; " + // Background color (ASU gold)
                        "-fx-border-color: #8C1D40; " + // Border color (ASU maroon)
                        "-fx-border-width: 5; " + // Border width
                        "-fx-border-radius: 10; " + //round corners
                        "-fx-background-radius: 10;"
        );
        layout.setAlignment(Pos.CENTER);


        // Title
        Text title = new Text("SunDevil Bookstore");
        title.setFont(Font.font("Times New Roman", 50));
        title.setFill(Color.web("#8C1D40"));
        StackPane header = new StackPane(title);
        header.setStyle("-fx-background-color: white; -fx-padding: 10;");
        layout.getChildren().add(header);

        // Input layout
        VBox inputLayout = new VBox(30);
        inputLayout.setAlignment(Pos.CENTER);
        inputLayout.setTranslateY(30);

        TextField asuIDField = new TextField();
        asuIDField.setPromptText("Enter your ASU ID");
        asuIDField.setMaxWidth(200);

        TextField passwordField = new TextField();
        passwordField.setPromptText("Enter your password");
        passwordField.setMaxWidth(200);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-font-size: 16px; -fx-background-color: #8C1D40; -fx-text-fill: #FFC627;");
        loginButton.setTranslateY(20);

        // Text for displaying error messages
        Text errorMessage = new Text();
        errorMessage.setFont(Font.font("Arial", 14)); // Smaller font for errors
        errorMessage.setFill(Color.RED);
        errorMessage.setVisible(false); // Initially hidden

        // Add inputs fields to input layout
        inputLayout.getChildren().addAll(asuIDField, passwordField, loginButton, errorMessage);

        // Add input layout to main layout
        layout.getChildren().add(inputLayout);

        // Handle login button click
        loginButton.setOnAction(e -> {
            String asuID = asuIDField.getText().trim();
            String password = passwordField.getText().trim();
            String accessLevel = checkCredentials(asuID, password);

            // Check if credentials are valid and route to the respective view
            if (accessLevel != null) {
                errorMessage.setVisible(false);
                switch (accessLevel) {
                    case "Admin":
                        showAdminView(primaryStage); // Switch to admin view
                        break;
                    case "Seller":
                        showSellerView(primaryStage); // Switch to seller view
                        break;
                    case "Buyer":
                        showBuyerView(primaryStage); // Switch to buyer view
                        break;
                    default:
                        errorMessage.setText("Unknown access level"); // No access level in users.txt for this user
                        errorMessage.setVisible(true);
                }
            } else {
                errorMessage.setText("Incorrect ASU ID or password. Please try again."); // Incorrect username or password
                errorMessage.setVisible(true);
            }
        });

        // Set up the scene
        Scene scene = new Scene(layout, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SunDevil Bookstore - Login");
        primaryStage.show();
    }

    // Check username, password, and accessLevel against users.txt
    private String checkCredentials(String asuID, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(" ");
                // Compare username and password against users.txt
                if (details.length == 3 && details[0].equals(asuID) && details[1].equals(password)) {
                    return details[2]; // Return access level
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace(); // Catch errors
        }
    return null; // Return null if no match
}

    // Show admin view
    private void showAdminView(Stage primaryStage) {
        System.out.println("Redirecting to Admin View...");
        new adminView(primaryStage).showAdminView(); // Navigate to admin view
    }

    // Show seller view
    private void showSellerView(Stage primaryStage) {
        System.out.println("Redirecting to Seller View...");
        new sellerView(primaryStage).showSellerView(); // Navigate to seller view
    }

    // Show buyer view
    private void showBuyerView(Stage primaryStage) {
        System.out.println("Redirecting to Buyer View...");
        new buyerView(primaryStage).showBuyerView(); // Navigate to buyer view
    }

    public static void main(String[] args) {
        launch(args); // Starts the JavaFX application
    }
}
