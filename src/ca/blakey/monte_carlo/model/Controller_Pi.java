package ca.blakey.monte_carlo.model;
//Copyright (c) <2015> <Phillip Blakey>
import java.awt.Button;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ca.blakey.monte_carlo.Main;
import ca.blakey.monte_carlo.model.ScreensController;

public class Controller_Pi implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	private int numThreads = 0;
	private int numTrials = 0;
	private double pi;
	
	@FXML private Button buttonSetThreads;
	@FXML private TextField numThreadsInput;
	@FXML private Button buttonSetTrials;
	@FXML private TextField numTrialsInput;
	@FXML private Button Simulate;
	@FXML private Text currentNumThreads;
	@FXML private Text currentNumTrials;
	@FXML private Text valueOfPi;
	@FXML private VBox PiVBox;
	@FXML private Text StatusLabel;
	@FXML private ProgressBar piProgressBar;

	  public void setScreenParent(ScreensController screenParent){
	        myController = screenParent;
	    }
	  
	  @FXML
		public void backToMain(){
			  myController.setScreen(Main.mainPageName);
		}
		

	public void setNumThreadsClicked(){
		try{
		numThreads = Integer.parseInt(numThreadsInput.getText());
		//currentNumThreads.setText(numThreadsInput.getText());
		}
		catch(NumberFormatException e){
			 StatusLabel.setText("Please enter an integer");
			 System.out.println("You did not enter an integer");
			 throw(e);
		}
		System.out.println(numThreads);
	}
	public void setNumTrialsClicked(){
		try{
		numTrials = Integer.parseInt(numTrialsInput.getText());
		//currentNumTrials.setText(numTrialsInput.getText());
		System.out.println(numTrials);
		}
		catch(NumberFormatException e){
			 StatusLabel.setText("Please enter an integer");
			 StatusLabel.setFill(Color.FIREBRICK);
			 System.out.println("You did not enter an integer");
			 throw(e);
		}
	}
	public void simulateClicked() throws Exception{
		
		try {
		setNumTrialsClicked();
		setNumThreadsClicked();
		}
		catch (NumberFormatException e){
			
			return;
		}
		MCRunnerNoAWS mCRunner= new MCRunnerNoAWS(numThreads, numTrials, 2, "piSimulation");
		valueOfPi.textProperty().bind(mCRunner.valueProperty().asString());
		piProgressBar.progressProperty().bind(mCRunner.progressProperty());
		Thread workingThread = new Thread(mCRunner);
		workingThread.setDaemon(true);
		workingThread.start();
		StatusLabel.setText("Finished");

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}

