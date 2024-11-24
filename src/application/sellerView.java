package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
        rectangle.setFill(Color.WHITE); // Set fill color
        rectangle.setStroke(Color.BLACK); // Set border color
        
        //text
		Text sellABook = new Text("Sell A Book");
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
        conditionComboBox.getItems().addAll("Used like new", "Moderately used", "Heavily used");
        conditionComboBox.setPromptText("Select Condition");
        
        //Buttons
        Button backButton = new Button("Go Back");
		backButton.setPrefSize(80,40);
		backButton.setStyle("-fx-font-size: 9px;");
		
		Button addToList = new Button("List My Book");
		addToList.setPrefSize(80,40);
		addToList.setStyle("-fx-font-size: 9px;");
		
		Button logout = new Button("Logout");
		addToList.setPrefSize(80,40);
		addToList.setStyle("-fx-font-size: 9px;");
		
		//Listeners
		// Add listener to update the result when Original Price and condition
		// are inputed.
        priceField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateResultField(priceField, conditionComboBox, resultField);
        });

        conditionComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateResultField(priceField, conditionComboBox, resultField);
        });
		
        //VBox and HBox Layout
        VBox vBox1 = new VBox(20);
        vBox1.getChildren().addAll(nameField, categoryComboBox, conditionComboBox, priceField);
        vBox1.setLayoutX(370);
        vBox1.setLayoutY(95);
        
        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(logout);
        vBox2.setLayoutX(10);
        vBox2.setLayoutY(10);
        
		HBox hBox1 = new HBox(340);
		hBox1.getChildren().addAll(backButton, addToList);
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
        backButton.setOnAction(e -> {
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
            String price = resultField.getText();

            // Convert condition to integer value (mapping)
            int conditionValue = 0;
            if ("Used like new".equals(condition)) {
                conditionValue = 1;
            } else if ("Moderately used".equals(condition)) {
                conditionValue = 2;
            } else if ("Heavily used".equals(condition)) {
                conditionValue = 3;
            }

            // Makes sure that the Original Price is a Double (error handling if not valid)
            double priceValue = 0;
            try {
                priceValue = Double.parseDouble(price);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid price input");
                
                //ALert the user the book was successfully added
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Unsuccessful");
                alert.setHeaderText(null);
                alert.setContentText("Please, fill out the form.");
                alert.showAndWait();
                
                return; // Exit if price is invalid
            }

            // Prepare the file and write the data
            File file = new File("src/application/books.txt");
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
            
            // Clear input fields after successful addition
            categoryComboBox.setValue(null);  
            conditionComboBox.setValue(null); 
            priceField.clear();               
            resultField.clear();              
            nameField.clear();
            
            //ALert the user the book was successfully added
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Book added successfully!");
            alert.showAndWait();
        });
		
        //Scene sellerScene = new Scene(layout, 800, 500);
        Scene sellerScene = new Scene(root, 800, 500);

        // Set the new scene on the stage
        stage.setScene(sellerScene);
	}
	
	//method to update resultPrice
	private void updateResultField(TextField priceField, ComboBox<String> conditionComboBox, TextField resultField) {
	    String priceText = priceField.getText();
	    String condition = conditionComboBox.getValue();
	    
	    if (priceText != null && !priceText.isEmpty() && condition != null) {
	        try {
	            double originalPrice = Double.parseDouble(priceText);
	            double resultPrice = originalPrice;

	            switch (condition) {
	                case "Used like new":
	                    resultPrice = originalPrice; // No discount
	                    break;
	                case "Moderately used":
	                    resultPrice = originalPrice * 0.90; // 10% discount
	                    break;
	                case "Heavily used":
	                    resultPrice = originalPrice * 0.75; // 25% discount
	                    break;
	            }

	            resultField.setText(String.format("%.2f", resultPrice)); // Update resultField
	        } catch (NumberFormatException e) {
	            resultField.setText("Invalid price"); // Handle invalid input gracefully
	        }
	    } else {
	        resultField.setText(""); // Clear resultField if inputs are incomplete
	    }
	}

}
