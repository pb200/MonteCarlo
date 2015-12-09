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
 * This Class is the controller class for the Pi calculation simulation screen. It contains
 * all the methods that are called when a user interacts with the GUI for the Pi calculation
 * Simulation.
 * 
 */
public class Controller_Pi implements Initializable, ControlledScreen {

	ScreensController myController;

	private int numThreads = 0;
	private long numTrials = 0;
	private double pi;

	@FXML
	private Button buttonSetThreads;
	@FXML
	private TextField numThreadsInput;
	@FXML
	private Button buttonSetTrials;
	@FXML
	private TextField numTrialsInput;
	@FXML
	private Button Simulate;
	@FXML
	private Text currentNumThreads;
	@FXML
	private Text currentNumTrials;
	@FXML
	private Text valueOfPi;
	@FXML
	private VBox PiVBox;
	@FXML
	private Text StatusLabel;
	@FXML
	private ProgressBar piProgressBar;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.blakey.monte_carlo.model.ControlledScreen#setScreenParent(ca.blakey.
	 * monte_carlo.model.ScreensController)
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
	public void setNumThreadsClicked() {
		try {
			numThreads = Integer.parseInt(numThreadsInput.getText());
			// currentNumThreads.setText(numThreadsInput.getText());
		} catch (NumberFormatException e) {
			StatusLabel.setText("Please enter an integer");
			System.out.println("You did not enter an integer");
			throw (e);
		}
		System.out.println(numThreads);
	}

	/**
	 * This method is run when the simulate button is clicked. This method takes 
	 * an integer from the text box, Number of Trials, and sets the private variable
	 * numTrials to this value. Should the input text in the text box be something
	 * other than an integer this method writes "Please enter an integer" on the GUI.
	 */
	public void setNumTrialsClicked() {
		try {
			numTrials = Long.parseLong(numTrialsInput.getText());
			// currentNumTrials.setText(numTrialsInput.getText());
			System.out.println(numTrials);
		} catch (NumberFormatException e) {
			StatusLabel.setText("Please enter an integer");
			StatusLabel.setFill(Color.FIREBRICK);
			System.out.println("You did not enter an integer");
			throw (e);
		}
	}

	/**
	 * @throws Exception
	 * 
	 * This method runs when the simulate button is clicked. This method runs the two
	 * methods setNumTrialsClicked, and setNumThreadsClicked.
	 * The method then creates an instance of MCRunner, binds the piProgressBar to 
	 * mCRunner's progress property, binds the valueOfPi variable to mCRunner's value
	 * property creates a new thread, makes this thread a Daemon thread,
	 *  then runs this thread. 
	 */
	public void simulateClicked() throws Exception {

		try {
			setNumTrialsClicked();
			setNumThreadsClicked();
			if(numTrials <1){
				throw new NumberFormatException();
			}
			if(numThreads < 1){
				throw new NumberFormatException();
			}
			if(numThreads > 8){
				throw new Exception();
			}
		} catch (NumberFormatException e) {
			return;
		}
		catch(Exception e){
			StatusLabel.setText("That is too many threads!!");
			StatusLabel.setFill(Color.FIREBRICK);
		}
		MCRunnerNoAWS mCRunner = new MCRunnerNoAWS(numThreads, numTrials, 2, "piSimulation");
		valueOfPi.textProperty().bind(mCRunner.valueProperty().asString());
		piProgressBar.progressProperty().bind(mCRunner.progressProperty());
		Thread workingThread = new Thread(mCRunner);
		workingThread.setDaemon(true);
		workingThread.start();
		StatusLabel.setText("Finished");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
