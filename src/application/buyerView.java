package application;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.geometry.Insets;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class buyerView {
	private Stage stage;

	public buyerView(Stage stage) {
		this.stage = stage;
	}


	public void showBuyerView() {
		// Root layout
		BorderPane root = new BorderPane();

		// HEADER: Title "SunDevil Bookstore"
		Text title = new Text("Buy-A-Book");
		title.setFont(Font.font("Times New Roman", 50));
		title.setFill(Color.MAROON);
		StackPane header = new StackPane(title);
		header.setStyle("-fx-background-color: white;");
		header.setPadding(new Insets(10));
		root.setTop(header);

		// CENTER: Form Layout
		VBox form = new VBox(15);
		form.setPadding(new Insets(20));
		form.setStyle("-fx-background-color: gold; -fx-border-color: maroon; -fx-border-width: 5px; -fx-border-radius: 10px;");
		form.setSpacing(15);

		// Form elements
		TextField nameField = new TextField();
		nameField.setPromptText("Enter book name");

		ComboBox<String> categoryDropdown = new ComboBox<>();
		categoryDropdown.getItems().addAll("Fiction", "Non-Fiction", "Textbook", "Other");
		categoryDropdown.setPromptText("Select Category");

		ComboBox<String> conditionDropdown = new ComboBox<>();
		conditionDropdown.getItems().addAll("New", "Like New", "Used", "Very Used");
		conditionDropdown.setPromptText("Select Condition");

		TextField priceField = new TextField();
		priceField.setPromptText("Enter max price");

		Button searchButton = new Button("Search");
		searchButton.setStyle("-fx-background-color: maroon; -fx-text-fill: gold; -fx-font-weight: bold;");

		// Add form elements to the VBox
		form.getChildren().addAll(
				createFormRow("Book Name:", nameField),
				createFormRow("Category:", categoryDropdown),
				createFormRow("Condition:", conditionDropdown),
				createFormRow("Max Price:", priceField),
				searchButton
		);

		// Add the form to the center of the layout
		StackPane formContainer = new StackPane(form);
		formContainer.setPadding(new Insets(20));
		root.setCenter(formContainer);

		// BOTTOM: Buttons for navigation
		HBox navigationButtons = new HBox(20);
		navigationButtons.setPadding(new Insets(15));
		navigationButtons.setStyle("-fx-background-color: lightgray;");
		navigationButtons.setSpacing(20);

		Button goBackButton = new Button("Go Back");
		Button logoutButton = new Button("Logout");
		goBackButton.setStyle("-fx-background-color: maroon; -fx-text-fill: gold; -fx-font-weight: bold;");
		logoutButton.setStyle("-fx-background-color: maroon; -fx-text-fill: gold; -fx-font-weight: bold;");

		navigationButtons.getChildren().addAll(goBackButton, logoutButton);
		navigationButtons.setPadding(new Insets(15));
		root.setBottom(navigationButtons);

		// Logout button action
		logoutButton.setOnAction(e -> {
			Main mainView = new Main();
			try {
				mainView.start(stage);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		// Go Back button action
		goBackButton.setOnAction(e -> {
			// Logic to go back (not implemented here)
			System.out.println("Go back button clicked!");
		});

		// Set the scene and stage
		Scene buyerScene = new Scene(root, 800, 500);
		stage.setScene(buyerScene);
		stage.setTitle("SunDevil Bookstore - Buyer View");
	}

	// Helper method to create a form row
	private HBox createFormRow(String labelText, Node inputField) {
		Text label = new Text(labelText);
		label.setFont(Font.font("Arial", 14));
		label.setFill(Color.MAROON);
		HBox row = new HBox(10, label, inputField);
		row.setSpacing(10);
		return row;
	}
}
