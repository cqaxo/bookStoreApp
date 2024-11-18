package application;

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
		
		Text category = new Text("Book Category: ");
		category.setX(250);
		category.setY(150);
		
		Text condition = new Text("Book Condition: ");
		condition.setX(250);
		condition.setY(200);
        
		Text originalPrice = new Text("Original Price: ");
		originalPrice.setX(250);
		originalPrice.setY(250);
		
		Text name = new Text("Name: ");
		name.setX(250);
		name.setY(110);
		
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
        Button backButton = new Button("Go Back");
		backButton.setPrefSize(80,40);
		backButton.setStyle("-fx-font-size: 9px;");
		
		Button addToList = new Button("List My Book");
		addToList.setPrefSize(80,40);
		addToList.setStyle("-fx-font-size: 9px;");
		
		Button logout = new Button("Logout");
		addToList.setPrefSize(80,40);
		addToList.setStyle("-fx-font-size: 9px;");
		
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
        root.getChildren().addAll(sellABook,name, category, condition, originalPrice);
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
		
        //Scene sellerScene = new Scene(layout, 800, 500);
        Scene sellerScene = new Scene(root, 800, 500);

        // Set the new scene on the stage
        stage.setScene(sellerScene);
	}
}
