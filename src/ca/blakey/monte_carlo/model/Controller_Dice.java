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

/**
 * @author phill_000
 *Copyright (c) <2015> <Phillip Blakey>
 *
 * This Class is the controller class for the Dice simulation screen. It contains
 * all the methods that are called when a user interacts with the GUI for the Dice
 * Simulation.
 */
public class Controller_Dice implements Initializable, ControlledScreen {

	ScreensController myController;

	private int numThreads = 0;
	private int numTrials = 0;
	private int numDice;
	
	@FXML
	private VBox DiceVBox;
	@FXML
	private TextField numTrialsInDice;
	@FXML
	private TextField numThreadsInDice;
	@FXML
	private TextField numDiceInDice;
	@FXML
	private Text numTrialsDice;
	@FXML
	private Text numThreadsDice;
	@FXML
	private Text numDiceDice;
	@FXML
	private Text avgSum;
	@FXML
	private Text statusLabelDice;
	@FXML
	private ProgressBar diceProgressBar;

	/* (non-Javadoc)
	 * @see ca.blakey.monte_carlo.model.ControlledScreen#setScreenParent(ca.blakey.monte_carlo.model.ScreensController)
	 */
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	/**
	 * This method is run when the back to main button is clicked. This sets the 
	 * screen to the home screen(initial screen).
	 */
	@FXML
	public void backToMain() {
		myController.setScreen(Main.mainPageName);
	}

	/**
	 * This method is run when the simulate button is clicked. This method takes 
	 * an integer from the text box, Number of Threads, and sets the private variable
	 * numThreads to this value. Should the input text in the text box be something
	 * other than an integer this method writes "Please enter an integer" on the GUI.
	 */
	public void setNumThreadsClickedDice() {
		try {
			numThreads = Integer.parseInt(numThreadsInDice.getText());
			// numThreadsDice.setText(numThreadsInDice.getText());
		} catch (NumberFormatException e) {
			statusLabelDice.setText("Please enter an integer");
			System.out.println("You did not enter an integer");
		}
		System.out.println(numThreads);
	}

	/**
	 * This method is run when the simulate button is clicked. This method takes 
	 * an integer from the text box, Number of Trials, and sets the private variable
	 * numTrials to this value. Should the input text in the text box be something
	 * other than an integer this method writes "Please enter an integer" on the GUI.
	 */
	public void setNumTrialsClickedDice() {
		try {
			numTrials = Integer.parseInt(numTrialsInDice.getText());
			// numTrialsDice.setText(numTrialsInDice.getText());
			System.out.println(numTrials);
		} catch (NumberFormatException e) {
			statusLabelDice.setText("Please enter an integer");
			statusLabelDice.setFill(Color.FIREBRICK);
			System.out.println("You did not enter an integer");
		}
	}

	/**
	 * This method is run when the simulate button is clicked. This method takes 
	 * an integer from the text box, Number of Dice, and sets the private variable
	 * numDice to this value. Should the input text in the text box be something
	 * other than an integer this method writes "Please enter an integer" on the GUI.
	 */
	public void setNumDiceClickedDice() {
		try {
			numDice = Integer.parseInt(numDiceInDice.getText());
			// numDiceDice.setText(numDiceInDice.getText());
		} catch (NumberFormatException e) {
			statusLabelDice.setText("Please enter an integer");
			statusLabelDice.setFill(Color.FIREBRICK);
			System.out.println("You did not enter an integer");
		}
	}

	/**
	 * @throws Exception 
	 * This method runs when the simulate button is clicked. This method runs the three
	 * methods setNumTrialsClickedDice, setNumThreadsClickedDice, and setNumDiceClickedDice.
	 * The method then creates an instance of MCRunner, binds the diceProgressBar to 
	 * mCRunner's progress property, binds the average sum variable to mCRunner's value
	 * property creates a new thread, makes this thread a Daemon thread,
	 *  then runs this thread. 
	 */
	public void simulateDiceClicked() throws Exception {
		try {
			setNumTrialsClickedDice();
			setNumThreadsClickedDice();
			setNumDiceClickedDice();
		} catch (NumberFormatException e) {
			return;
		}

		MCRunnerNoAWS mCRunner = new MCRunnerNoAWS(numThreads, numTrials, numDice, "diceRoll");
		diceProgressBar.progressProperty().bind(mCRunner.progressProperty());
		avgSum.textProperty().bind(mCRunner.valueProperty().asString());
		Thread workingThread = new Thread(mCRunner);
		workingThread.setDaemon(true);
		workingThread.start();

		statusLabelDice.setText("Finished");
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
