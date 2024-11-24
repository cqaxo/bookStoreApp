package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setWidth(800);
        primaryStage.setHeight(500);



        // Create UI elements

        VBox layout = new VBox(20); // VBox with 20px spacing
        layout.setAlignment(Pos.CENTER);
        layout.setStyle(
                "-fx-padding: 30; " +
                        "-fx-background-color: #FFC627; " + // Background color (ASU gold)
                        "-fx-border-color: #8C1D40; " + // Border color (ASU maroon)
                        "-fx-border-width: 5; " + // Border width
                        "-fx-border-radius: 10; " + //round corners
                        "-fx-background-radius: 10;"
        );

        // SunDevil Book Store title on 3 lines
        Text titleLine1 = new Text("SunDevil");
        titleLine1.setFont(Font.font("Impact", 36));
        titleLine1.setFill(Color.web("#8C1D40")); // ASU maroon

        Text titleLine2 = new Text("Book");
        titleLine2.setFont(Font.font("Impact", 36));
        titleLine2.setFill(Color.web("#8C1D40"));

        Text titleLine3 = new Text("Store");
        titleLine3.setFont(Font.font("Impact", 36));
        titleLine3.setFill(Color.web("#8C1D40"));


        // group the title lines
        VBox titleBox = new VBox(5);
        titleBox.getChildren().addAll(titleLine1, titleLine2, titleLine3);
        titleBox.setAlignment(Pos.CENTER); // centering the title text

        TextField asuIDField = new TextField();
        asuIDField.setPromptText("Enter your ASU ID");
        asuIDField.setMaxWidth(200);

        TextField passwordField = new TextField();
        passwordField.setPromptText("Enter your password");
        passwordField.setMaxWidth(200);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-font-size: 16px; -fx-background-color: #8C1D40; -fx-text-fill: #FFC627;");

        // Text for displaying error messages
        Text errorMessage = new Text();
        errorMessage.setFont(Font.font("Arial", 14)); // Smaller font for errors
        errorMessage.setFill(Color.RED);
        errorMessage.setVisible(false); // Initially hidden




        // Add UI elements to layout
        layout.getChildren().addAll(titleBox, asuIDField, passwordField, loginButton, errorMessage);

        // Handle login button click
        loginButton.setOnAction(e -> {
            String asuID = asuIDField.getText();
            String password = passwordField.getText();

            // Check if credentials are valid and route to the respective view
            if (checkCredentials(asuID, password)) {
                errorMessage.setVisible(false);
                if (asuID.equals("admin")) {
                    showAdminView(primaryStage);
                } else if (asuID.equals("seller")) {
                    showSellerView(primaryStage);
                } else if (asuID.equals("buyer")) {
                    showBuyerView(primaryStage);
                }
            } else {
                // Display error message
                errorMessage.setText("Incorrect ASU ID or password. Please try again.");
                errorMessage.setVisible(true);
            }
        });

        // Set up the scene
        Scene scene = new Scene(layout, 800, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /* 
    	Temporary way of logging in
    	will need to implement actual authentication login later
    
    */
    private boolean checkCredentials(String asuID, String password) {
        // Placeholder for actual authentication logic
        return !asuID.isEmpty() && !password.isEmpty();
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
