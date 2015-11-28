package ca.blakey.monte_carlo;

import java.net.URL;

//Copyright (c) <2015> <Phillip Blakey>
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import ca.blakey.monte_carlo.model.ScreensController;
import ca.blakey.monte_carlo.Main;

public class Main extends Application{
	
    public static String screen1ID = "main";
    public static String screen1File = "/ca/blakey/monte_carlo/view/monte_carlo.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "/ca/blakey/monte_carlo/view/monte_carlo_dice.fxml";
    public static String screen3ID = "screen3";
    public static String screen3File = "/ca/blakey/monte_carlo/view/monte_carlo_pi.fxml";
	
	 public static void main(String[] args) {
	        launch(args);
	    }
	 
	 
 @Override
 public void start(Stage stage) throws Exception {
 
     ScreensController mainContainer = new ScreensController();
     mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
     mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
     mainContainer.loadScreen(Main.screen3ID,Main.screen3File);
     
     mainContainer.setScreen(Main.screen1ID);
     
     Group root = new Group();
     root.getChildren().addAll(mainContainer);
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
 }
}
