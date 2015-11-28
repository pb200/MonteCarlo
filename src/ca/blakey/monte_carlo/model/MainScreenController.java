package ca.blakey.monte_carlo.model;

import ca.blakey.monte_carlo.model.ScreensController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ca.blakey.monte_carlo.model.ControlledScreen;

import java.net.URL;
import java.util.ResourceBundle;

import ca.blakey.monte_carlo.Main;

public class MainScreenController implements Initializable, ControlledScreen {

	ScreensController myController;
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
	
	  public void setScreenParent(ScreensController screenParent){
	        myController = screenParent;
	    }
	
	@FXML
	public void setDiceSimVisible(){
		  myController.setScreen(Main.screen2ID);
	}
	
	@FXML
	public void setPiSimVisible(){
		 myController.setScreen(Main.screen3ID);
	}
}
