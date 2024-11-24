package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class sellerView {
    private Stage stage;

    public sellerView(Stage stage) {
        this.stage = stage;
    }

    public void showSellerView() {

        Group root = new Group();

        // Create a rectangle at (100, 50) with width 600 and height 350
        Rectangle rectangle = new Rectangle(100, 50, 600, 350);

        // Set the fill color to ASU gold
        rectangle.setFill(Color.web("#FFC627")); // ASU gold

        // Set the border (stroke) color to ASU maroon
        rectangle.setStroke(Color.web("#8C1D40")); // ASU maroon


        rectangle.setStrokeWidth(5);

        // Round the corners
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);


        //text
        Text sellABook = new Text("Sell-A-Book");
        sellABook.setFont(Font.font("Impact", 30));
        sellABook.setFill(Color.web("#8C1D40"));
        sellABook.setX(100);
        sellABook.setY(40);

        Text categoryText = new Text("Book Category: ");
        categoryText.setX(250);
        categoryText.setY(150);

        Text conditionText = new Text("Book Condition: ");
        conditionText.setX(250);
        conditionText.setY(200);

        Text originalPrice = new Text("Original Price: ");
        originalPrice.setX(250);
        originalPrice.setY(250);

        Text nameText = new Text("Name: ");
        nameText.setX(250);
        nameText.setY(110);

        Text buyingPrice = new Text("Buying Price: ");

        // Text field
        TextField priceField = new TextField();
        priceField.setPromptText("Enter price");
        priceField.setPrefWidth(80);   // Preferred width of 200px
        priceField.setPrefHeight(10);   // Preferred height of 30px

        // Add numeric-only validation with up to 2 decimal places
        priceField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
                priceField.setText(oldValue); // Revert to the previous valid value
            }
        });

        TextField nameField = new TextField();
        nameField.setPromptText("Enter name");
        nameField.setPrefWidth(80);   // Preferred width of 200px
        nameField.setPrefHeight(10);   // Preferred height of 30px

        TextField resultField = new TextField("Calculated Result");
        resultField.setDisable(true);  // Completely disables the TextField

        //Combo boxes
        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Fiction", "Non-Fiction", "Science", "History");
        categoryComboBox.setPromptText("Select Category");

        ComboBox<String> conditionComboBox = new ComboBox<>();
        conditionComboBox.getItems().addAll("Mint", "Average", "poor");
        conditionComboBox.setPromptText("Select Condition");

        //Buttons
        Button logoutButton = new Button("Logout");
        logoutButton.setPrefSize(80,40);
        logoutButton.setStyle("-fx-background-color: #8C1D40; -fx-text-fill: #FFC627; -fx-font-weight: bold;");

        Button addToList = new Button("List My Book");
        addToList.setPrefSize(100,40);
        addToList.setStyle("-fx-background-color: #8C1D40; -fx-text-fill: #FFC627; -fx-font-weight: bold;");


        //VBox and HBox Layout
        VBox vBox1 = new VBox(20);
        vBox1.getChildren().addAll(nameField, categoryComboBox, conditionComboBox, priceField);
        vBox1.setLayoutX(370);
        vBox1.setLayoutY(95);



        HBox hBox1 = new HBox(340);
        hBox1.getChildren().addAll(logoutButton, addToList);
        hBox1.setLayoutX(150);
        hBox1.setLayoutY(320);

        HBox hBox2 = new HBox(10);
        hBox2.getChildren().addAll(buyingPrice, resultField);
        hBox2.setLayoutX(300);
        hBox2.setLayoutY(300);

        //StackPane layout = new StackPane();

        root.getChildren().add(rectangle);
        root.getChildren().addAll(sellABook,nameText, categoryText, conditionText, originalPrice);
        root.getChildren().addAll(vBox1, hBox1, hBox2);

        // Add action to the Go Back button
        logoutButton.setOnAction(e -> {
            Main mainView = new Main();
            try {
                mainView.start(stage); // Navigate back to the main screen
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        addToList.setOnAction(e -> {
            // Retrieve input data
            String name = nameField.getText();
            String category = categoryComboBox.getValue();
            String condition = conditionComboBox.getValue();
            String price = priceField.getText();

            // Convert condition to integer value (mapping)
            int conditionValue = 0;
            if ("Mint".equals(condition)) {
                conditionValue = 1;
            } else if ("Average".equals(condition)) {
                conditionValue = 2;
            } else if ("Poor".equals(condition)) {
                conditionValue = 3;
            }

            // Makes sure that the Original Price is a Double (error handling if not valid)
            double priceValue = 0;
            try {
                priceValue = Double.parseDouble(price);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid price input");
                return; // Exit if price is invalid
            }

            // Prepare the file and write the data
            File file = new File("books.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) { // true to append to the file
                // Write the name on the first line
                writer.write(name);
                writer.newLine();

                // Write category, condition, and price on the second line
                writer.write(category + " " + conditionValue + " " + priceValue);
                writer.newLine();

                System.out.println("Book listed successfully!");

            } catch (IOException ex) {
                System.out.println("An error occurred while writing to the file.");
                ex.printStackTrace();
            }
        });

        //Scene sellerScene = new Scene(layout, 800, 500);
        Scene sellerScene = new Scene(root, 800, 500);

        // Set the new scene on the stage
        stage.setScene(sellerScene);
        stage.setTitle("SunDevil Bookstore - Seller View");
    }
}
