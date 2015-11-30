package ca.blakey.monte_carlo;


//Copyright (c) <2015> <Phillip Blakey>
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.stage.Stage;
import ca.blakey.monte_carlo.model.ScreensController;
import ca.blakey.monte_carlo.Main;

public class Main extends Application {

	public static String mainPageName = "main";
	public static String mainPageFxmlPath = "/ca/blakey/monte_carlo/view/monte_carlo.fxml";
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
		mainContainer.loadScreen(Main.mainPageName, Main.mainPageFxmlPath);
		mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
		mainContainer.loadScreen(Main.screen3ID, Main.screen3File);

		mainContainer.setScreen(Main.mainPageName);

		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
