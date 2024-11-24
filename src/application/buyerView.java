package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class buyerView {
	private Stage stage;
	
	public buyerView(Stage stage) {
		this.stage = stage;
	}
	
	public void showBuyerView() {
		
		Group root = new Group();
		
		int page = 1;
		
		// rectangle (x, y, width, height)
        Rectangle rectangle = new Rectangle(0, 100, 800, 40);
        rectangle.setFill(Color.WHITE); // Set fill color
        rectangle.setStroke(Color.BLACK); // Set border color
		
		//buttons
		Button backButton = new Button("Logout");
		backButton.setPrefSize(80,40);
		backButton.setStyle("-fx-font-size: 9px;");
		
		//Text
		Text genre = new Text("Genre");
		genre.setX(70);
		genre.setY(125);
		
		Text title = new Text("Title");
		title.setX(140);
		title.setY(125);
		
		Text condition = new Text("Condition");
		condition.setX(500);
		condition.setY(125);
		
		Text amount = new Text("Amount");
		amount.setX(730);
		amount.setY(125);
		
		//VBox and HBox formatting
		VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(backButton);
        vBox1.setLayoutX(700);
        vBox1.setLayoutY(10);
        
        root.getChildren().addAll(vBox1);
        root.getChildren().addAll(rectangle);
        root.getChildren().addAll(genre, title, condition, amount);
		
		Scene buyerScene = new Scene(root, 800, 500);
		
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
        stage.setScene(buyerScene);
	}

}
