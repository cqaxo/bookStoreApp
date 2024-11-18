package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class adminView {
	private Stage stage;
	
	public adminView(Stage stage) {
		this.stage = stage;
	}
	
	public void showAdminView() {
		
		Group root = new Group();
		
		
		// rectangle (x, y, width, height)
        Rectangle rectangle = new Rectangle(0, 100, 800, 40);
        rectangle.setFill(Color.WHITE); // Set fill color
        rectangle.setStroke(Color.BLACK); // Set border color
        
        Rectangle rectangle2 = new Rectangle(0, 175, 800, 40);
        rectangle2.setFill(Color.WHITE); // Set fill color
        rectangle2.setStroke(Color.BLACK); // Set border color
		
		//buttons
		Button backButton = new Button("Logout");
		backButton.setPrefSize(80,40);
		backButton.setStyle("-fx-font-size: 9px;");
		
		Button resetButton = new Button("Reset Password");
		backButton.setPrefSize(80,40);
		backButton.setStyle("-fx-font-size: 9px;");
		
		//Text
		Text trans = new Text("Transactions");
		trans.setX(20);
		trans.setY(125);
		
		Text user = new Text("Users");
		user.setX(20);
		user.setY(200);
		
		Text access = new Text("User Access");
		access.setX(125);
		access.setY(200);
		
		//Text passR = new Text("Reset password");
		//passR.setX(400);
		//passR.setY(200);
		
		//VBox and HBox formatting
		VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(backButton);
        vBox1.setLayoutX(700);
        vBox1.setLayoutY(10);
        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(resetButton);
        vBox2.setLayoutX(400);
        vBox2.setLayoutY(185);
        
        root.getChildren().addAll(vBox1);
        root.getChildren().addAll(rectangle);
        root.getChildren().addAll(trans);
        root.getChildren().addAll(rectangle2);
        root.getChildren().addAll(vBox2);
        root.getChildren().addAll(user, access);
        
		
		Scene adminScene = new Scene(root, 800, 500);
		
		//Action for logout button
		backButton.setOnAction(e -> {
            Main mainView = new Main();
            try {
                mainView.start(stage); // Navigate back to the main screen
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Set the new scene on the stage
        stage.setScene(adminScene);
	}

}
