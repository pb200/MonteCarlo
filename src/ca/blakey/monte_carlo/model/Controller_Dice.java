package ca.blakey.monte_carlo.model;

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

public class Controller_Dice implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	private int numThreads = 0;
	private int numTrials = 0;
	private int numDice;
	@FXML private VBox DiceVBox;
	@FXML private TextField numTrialsInDice;
	@FXML private TextField numThreadsInDice;
	@FXML private TextField numDiceInDice;
	@FXML private Text numTrialsDice;
	@FXML private Text numThreadsDice;
	@FXML private Text numDiceDice;
	@FXML private Text avgSum;
	@FXML private Text statusLabelDice;
	@FXML private ProgressBar diceProgressBar;
	  public void setScreenParent(ScreensController screenParent){
	        myController = screenParent;
	    }
	  @FXML
			public void backToMain(){
				  myController.setScreen(Main.mainPageName);
			}
	public void setNumThreadsClickedDice(){
		try{
		numThreads = Integer.parseInt(numThreadsInDice.getText());
	//	numThreadsDice.setText(numThreadsInDice.getText());
		}
		catch(NumberFormatException e){
			 statusLabelDice.setText("Please enter an integer");
			 System.out.println("You did not enter an integer");
		}
		System.out.println(numThreads);
	}
	public void setNumTrialsClickedDice(){
		try{
		numTrials = Integer.parseInt(numTrialsInDice.getText());
		//numTrialsDice.setText(numTrialsInDice.getText());
		System.out.println(numTrials);
		}
		catch(NumberFormatException e){
			 statusLabelDice.setText("Please enter an integer");
			 statusLabelDice.setFill(Color.FIREBRICK);
			 System.out.println("You did not enter an integer");
		}
	}
	public void setNumDiceClickedDice(){
		try{
		numDice = Integer.parseInt(numDiceInDice.getText());
		//numDiceDice.setText(numDiceInDice.getText());
		}
		catch(NumberFormatException e){
			 statusLabelDice.setText("Please enter an integer");
			 statusLabelDice.setFill(Color.FIREBRICK);
			 System.out.println("You did not enter an integer");
		}
	}
	public void simulateDiceClicked() throws Exception{
		try {
			setNumTrialsClickedDice();
			setNumThreadsClickedDice();
			setNumDiceClickedDice();
			}
			catch (NumberFormatException e){
				
				return;
			}
		
		MCRunnerNoAWS mCRunner= new MCRunnerNoAWS(numThreads, numTrials, numDice, "diceRoll");
		diceProgressBar.progressProperty().bind(mCRunner.progressProperty());
		avgSum.textProperty().bind(mCRunner.valueProperty().asString());
		Thread workingThread = new Thread(mCRunner);
		workingThread.setDaemon(true);
		workingThread.start();
			
		statusLabelDice.setText("Finished");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}

