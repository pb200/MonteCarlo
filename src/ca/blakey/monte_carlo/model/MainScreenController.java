package ca.blakey.monte_carlo.model;

import ca.blakey.monte_carlo.model.ScreensController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ca.blakey.monte_carlo.model.ControlledScreen;

import java.net.URL;
import java.util.ResourceBundle;

import ca.blakey.monte_carlo.Main;
	
/**
 * @author phill_000
 *Copyright (c) <2015> <Phillip Blakey>
 *
 * This Class is the controller class for the Main screen. It contains
 * all the methods that are called when a user interacts with the GUI for the Main Screen.
 * 
 *
 */
public class MainScreenController implements Initializable, ControlledScreen {

	ScreensController myController;
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
	
	  /* (non-Javadoc)
	 * @see ca.blakey.monte_carlo.model.ControlledScreen#setScreenParent(ca.blakey.monte_carlo.model.ScreensController)
	 */
	public void setScreenParent(ScreensController screenParent){
	        myController = screenParent;
	    }
	
	/**
	 * This method is run when the button Dice Simulation is clicked. This method sets 
	 * the screen to the dice simulation screen.
	 */
	@FXML
	public void setDiceSimVisible(){
		  myController.setScreen(Main.screen2ID);
	}
	
	/**
	 * This method is run when the button Pi Simulation is clicked. This method sets 
	 * the screen to the Pi simulation screen.
	 */
	@FXML
	public void setPiSimVisible(){
		 myController.setScreen(Main.screen3ID);
	}
	@FXML
	public void setBuffinSimVisible(){
		myController.setScreen(Main.screen4ID);
	}
}
