package ca.blakey.monte_carlo;

import java.net.URL;

//Copyright (c) <2015> <Phillip Blakey>
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class Main extends Application{
	 public static void main(String[] args) {
	        launch(args);
	    }
 @Override
 public void start(Stage stage) throws Exception {

     FXMLLoader loader = new FXMLLoader();
     URL tmp = Main.class.getResource("view/monte_carlo.fxml");
     loader.setLocation(tmp);

     Parent root = loader.load();

     Scene scene = new Scene(root, 700, 500);

     stage.setTitle("FXML Welcome");
     stage.setScene(scene);
     stage.show();
 }
}
