package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;

//Need to implement adding a user, tracking users, tracking transactions, and reseting password

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class adminView {
	private Stage stage;
	private ListView userListName = new ListView();
	//private ListView userListPass = new ListView();
	//private ListView userListAcc = new ListView();
	private ListView tranList = new ListView();
	// List for Users
    List<userInfo> users = new ArrayList<>();
    
    // list for transactions
    List<transactionInfo> transactions = new ArrayList<>();
	
	public adminView(Stage stage) {
		this.stage = stage;
	}
	
	public void addUser(String x, String y, String z) {
		
		File file = new File("users.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) { // true to append to the file
            // Write the name on the first line
            writer.write(x +" " + y + " " + z);
            writer.newLine();
            System.out.println("User created successfully!");

        } 
        catch (IOException ex) {
            System.out.println("An error occurred while writing to the file.");
            ex.printStackTrace();
        }
    };
	
	public void showAdminView() {
		
		Group root = new Group();
		
		populateData();
		
		
		
		// rectangle (x, y, width, height)
        Rectangle rectangle = new Rectangle(0, 80, 800, 60);
        rectangle.setFill(Color.web("#FFC627")); // Set fill color
        rectangle.setStroke(Color.web("#8C1D40")); // Set border color
        
        Rectangle rectangle2 = new Rectangle(0, 250, 800, 40);
        rectangle2.setFill(Color.web("#FFC627")); // Set fill color
        rectangle2.setStroke(Color.web("#8C1D40")); // Set border color
        
        Rectangle layout = new Rectangle(0,0,800,500); // VBox with 20px spacing 
        layout.setFill(Color.web("#FFC627"));
        /*Keeping this here for reference to the rest of the project colors and layout
         layout.setStyle(
            "-fx-padding: 30; " +
            "-fx-background-color: #FFC627; " + // Background color (ASU gold)
            "-fx-border-color: #8C1D40; " + // Border color (ASU maroon)
            "-fx-border-width: 5; " + // Border width 
            "-fx-border-radius: 10; " + //round corners
            "-fx-background-radius: 10;" 
        );
        */
        
		
		//buttons
		Button backButton = new Button("Logout");
		backButton.setPrefSize(80,40);
		backButton.setStyle("-fx-font-size: 9px;");
		
		Button resetButton = new Button("Reset Password");
		backButton.setPrefSize(80,40);
		backButton.setStyle("-fx-font-size: 9px;");
		
		Button addUButton = new Button("Add User");
		backButton.setPrefSize(80,40);
		backButton.setStyle("-fx-font-size: 9px;");
		
		//Text
		
		
        
		Text trans = new Text("Transactions");
		trans.setX(20);
		trans.setY(100);
		
		Text t1 = new Text("Buyer");
		t1.setX(20);
		t1.setY(130);
		
		Text t2 = new Text("Book");
		t2.setX(65);
		t2.setY(130);
		
		Text t3 = new Text("Condition");
		t3.setX(120);
		t3.setY(130);
		
		Text t4 = new Text("Price");
		t4.setX(190);
		t4.setY(130);
		
		Text user = new Text("Users");
		user.setX(20);
		user.setY(275);
		
		Text accesss = new Text("User Access");
		accesss.setX(140);
		accesss.setY(275);
		
		Text passwordT = new Text("Password");
		passwordT.setX(75);
		passwordT.setY(275);
		
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
        vBox2.setLayoutY(260);
        VBox vBox3 = new VBox();
        vBox3.getChildren().addAll(addUButton);
        vBox3.setLayoutX(500);
        vBox3.setLayoutY(260);
        HBox vBox4 = new HBox();
        vBox4.setSpacing(5);
        vBox4.getChildren().addAll(userListName);
        vBox4.setLayoutX(15);
        vBox4.setLayoutY(300);
        userListName.setPrefHeight(90);
        
        HBox vBox5 = new HBox();
        vBox5.setSpacing(5);
        vBox5.getChildren().addAll(tranList);
        vBox5.setLayoutX(15);
        vBox5.setLayoutY(150);
        tranList.setPrefHeight(90);
        
        root.getChildren().addAll(layout, vBox1);
        root.getChildren().addAll(rectangle);
        root.getChildren().addAll(trans);
        root.getChildren().addAll(rectangle2);
        root.getChildren().addAll(vBox2, vBox3, vBox4, vBox5);
        root.getChildren().addAll(user, t1, t2,t3,t4);
        root.getChildren().addAll(accesss, passwordT);
        
		
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
		
		addUButton.setOnAction(e -> {
            showAddUserView();
            
        });
		resetButton.setOnAction(e -> {
            resetPassword();
            
        });
		

        // Set the new scene on the stage
        stage.setScene(adminScene);
	}
	public void showAddUserView() {
		
		Group root = new Group();
	
		Text cUser = new Text("Create User:");
		cUser.setX(120);
		cUser.setY(50);
		
		Text userN = new Text("Username: ");
		userN.setX(300);
		userN.setY(110);
		
		Text passw = new Text("Password: ");
		passw.setX(300);
		passw.setY(155);
		
		Text access = new Text("Access: ");
		access.setX(300);
		access.setY(200);
		
		TextField nameField = new TextField();
		nameField.setPromptText("Enter name");
		nameField.setPrefWidth(80);   // Preferred width of 200px
        nameField.setPrefHeight(10);
        
        TextField passField = new TextField();
		passField.setPromptText("Enter password");
		passField.setPrefWidth(80);   // Preferred width of 200px
        passField.setPrefHeight(10);
		
		
		ComboBox<String> accessComboBox = new ComboBox<>();
        accessComboBox.getItems().addAll("Buyer", "Seller", "Admin");
        accessComboBox.setPromptText("Select Role");
        
        Button backButton = new Button("Go Back");
		backButton.setPrefSize(80,40);
		backButton.setStyle("-fx-font-size: 9px;");
		
		Button addButton = new Button("Add User");
		backButton.setPrefSize(80,40);
		backButton.setStyle("-fx-font-size: 9px;");
        
        VBox vBox1 = new VBox(20);
        vBox1.getChildren().addAll(nameField, passField, accessComboBox);
        vBox1.setLayoutX(370);
        vBox1.setLayoutY(95);
        
        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(backButton);
        vBox2.setLayoutX(700);
        vBox2.setLayoutY(10);
        
        VBox vBox3 = new VBox(20);
        vBox3.getChildren().addAll(addButton);
        vBox3.setLayoutX(325);
        vBox3.setLayoutY(225);
		
        root.getChildren().addAll(cUser, userN, passw, access);
        root.getChildren().addAll(vBox1, vBox2, vBox3);
		
		Scene adminScene = new Scene(root, 800, 500);
		
		backButton.setOnAction(e -> {
            showAdminView();
            });
		
		addButton.setOnAction(e -> {
			String name = nameField.getText();
			String pas = passField.getText();
			String ac = accessComboBox.getValue();
			
			if (name.equals("")) {
				System.out.println("Please enter Username.");	
			}
			else if(pas.equals("")) {
				System.out.println("Please enter password.");
			}
			else if(ac == null) {
				System.out.println("Please select Role.");
			}
			else {
				addUser(name, pas, ac);
				showAdminView();
			}
			
		});
			
            
            
		stage.setScene(adminScene);
	}
	public void populateData() {
		users.clear();
		transactions.clear();
		tranList.getItems().clear();
		userListName.getItems().clear();
		//userListPass.getItems().clear(); Commenting these for now in case I need them later
		//userListAcc.getItems().clear();
		try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] details = line.split(" "); // Reads line for  username, password, and access
                String name = details[0];
            	String password = details[1];
                String access = details[2];
                users.add(new userInfo(name, password, access));
                }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		for (int i = 0; i < users.size(); i++) {
			userListName.getItems().add(users.get(i).name + "        " + users.get(i).pass + "       " + users.get(i).access);
		}
		
		try (BufferedReader readert = new BufferedReader(new FileReader("transactions.txt"))) {
            String linet;
            while ((linet = readert.readLine()) != null) {
            	String[] details = linet.split(" "); // Reads line for  buyer, book name, condition, and price
                String buyer = details[0];
            	String book = details[1];
                String condition = details[2];
                String price = details[3];
                transactions.add(new transactionInfo(buyer, book, condition, price));
                }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		for (int i = 0; i < transactions.size(); i++) {
			tranList.getItems().add(transactions.get(i).name + "        " + transactions.get(i).bName + "        " + transactions.get(i).condition + "        " + transactions.get(i).price);
		
		}
		
	}
	
	public void resetPassword() {
		Group root = new Group();
		
		TextField nameField = new TextField();
		nameField.setPromptText("Enter name");
		nameField.setPrefWidth(80);   // Preferred width of 200px
        nameField.setPrefHeight(10);
        
        TextField passField = new TextField();
		passField.setPromptText("Enter password");
		passField.setPrefWidth(80);   // Preferred width of 200px
        passField.setPrefHeight(10);
        
        Text userN = new Text("Username: ");
		userN.setX(300);
		userN.setY(110);
		
		Text passw = new Text("Password: ");
		passw.setX(300);
		passw.setY(155);
		
		VBox vBox1 = new VBox(20);
        vBox1.getChildren().addAll(nameField, passField);
        vBox1.setLayoutX(370);
        vBox1.setLayoutY(95);
		
		Button backButton = new Button("Go Back");
		backButton.setPrefSize(80,40);
		backButton.setStyle("-fx-font-size: 9px;");
		
		Button addButton = new Button("Reset Password"); //pay no attention to the add button definitely wasn't a copy paste
		backButton.setPrefSize(80,40);
		backButton.setStyle("-fx-font-size: 9px;");
		
		VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(backButton);
        vBox2.setLayoutX(700);
        vBox2.setLayoutY(10);
        
        VBox vBox3 = new VBox(20);
        vBox3.getChildren().addAll(addButton);
        vBox3.setLayoutX(325);
        vBox3.setLayoutY(225);
        
        
        root.getChildren().addAll(vBox1, vBox2, vBox3);
        root.getChildren().addAll(userN, passw);
        
        backButton.setOnAction(e -> {
            showAdminView();
            });
        addButton.setOnAction(e -> {
        	String nameF = nameField.getText();
			String pas = passField.getText();
			String oldContent = "";
        	
			if (nameF.equals("")) {
				System.out.println("Please enter Username.");	
			}
			else if(pas.equals("")) {
				System.out.println("Please enter password.");
			}
			else {
				
			
        	try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] details = line.split(" ");
                    if(details[0].equals(nameF)) {
                    		oldContent = oldContent + details[0] + " " + pas + " " + details[2] + System.lineSeparator();
                    	}
                    else {
                    	oldContent = oldContent + details[0] + " " + details[1] + " " + details[2] + System.lineSeparator();
                        
                    }
                }
                System.out.println(oldContent);
                reader.close();
                try {Thread.sleep(1000);}
                catch (InterruptedException e2) { Thread.currentThread().interrupt();}
                BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
                writer.write(oldContent);
                writer.close();
            } 
        	catch (IOException e1) {
                e1.printStackTrace();
            }
        	
			}
        	
        	
		showAdminView();
			
			
		});
		
		Scene adminScene = new Scene(root, 800, 500);
		stage.setScene(adminScene);
	}
}
