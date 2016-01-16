package ca.blakey.monte_carlo.model;

import static javafx.concurrent.Worker.State.RUNNING;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
import javafx.scene.text.TextFlow;
import ca.blakey.monte_carlo.Main;
import ca.blakey.monte_carlo.model.ScreensController;

/**
 * @author phill_000 Copyright (c) <2015> <Phillip Blakey>
 *
 *         This Class is the controller class for the Pi calculation simulation
 *         screen. It contains all the methods that are called when a user
 *         interacts with the GUI for the Pi calculation Simulation.
 * 
 */
public class Controller_Buffin implements Initializable, ControlledScreen {

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
	private Text finishLabel;
	@FXML
	private ProgressBar piProgressBar;
    @FXML
    private Button cancelBtn;
    @FXML
    private Text statusLabel;
    @FXML
    private Text threadStatusLabel;
    @FXML
    private Text trialStatusLabel;

	private boolean inputError;
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
		this.reset();
		myController.setScreen(Main.mainPageName);
	}

	/**
	 * This method is run when the simulate button is clicked. This method takes
	 * an integer from the text box, Number of Threads, and sets the private
	 * variable numThreads to this value. Should the input text in the text box
	 * be something other than an integer this method writes
	 * "Please enter an integer" on the GUI.
	 */
	public void setNumThreadsClicked() {
		String numThreadsString = numThreadsInput.getText();
		if(Double.parseDouble(numThreadsString) %1 != 0){
			this.inputError = true;
			threadStatusLabel.setText("The number of threads must be a whole number.");
			threadStatusLabel.setFill(Color.FIREBRICK);
		}
		numThreads = (int) Double.parseDouble(numThreadsString);
		}



	/**
	 * This method is run when the simulate button is clicked. This method takes
	 * an integer from the text box, Number of Trials, and sets the private
	 * variable numTrials to this value. Should the input text in the text box
	 * be something other than an integer this method writes
	 * "Please enter an integer" on the GUI.
	 */
	public void setNumTrialsClicked() {
		String numTrialsString = numTrialsInput.getText();
		if(Double.parseDouble(numTrialsString) %1 != 0){
			this.inputError = true;
			trialStatusLabel.setText("The number of trials must be a whole number.");
			trialStatusLabel.setFill(Color.FIREBRICK);
		}
		numTrials = (long) Double.parseDouble(numTrialsString);
		System.out.println(numTrials);
	}

	public void descriptionDialogClicked() {
		Alert alert = new Alert(AlertType.INFORMATION);
        String titleTxt="HELLO";
		alert.setTitle(titleTxt);
		alert.setHeaderText("Dice simulation");
		String s = "This calculates the value of pi using monte carlo methods";
		alert.setContentText(s);
		alert.show();
	}
	public void reset(){
		inputError = false;
		if(statusLabel.textProperty().isBound() == true){
			statusLabel.textProperty().unbind();
			valueOfPi.textProperty().unbind();
			piProgressBar.progressProperty().unbind();
			}
		statusLabel.setText("");
		valueOfPi.setText("");
		piProgressBar.setProgress(0.0);
		trialStatusLabel.setText("");
		threadStatusLabel.setText("");
	}
	/**
	 * @throws Exception
	 * 
	 *             This method runs when the simulate button is clicked. This
	 *             method runs the two methods setNumTrialsClicked, and
	 *             setNumThreadsClicked. The method then creates an instance of
	 *             MCRunner, binds the piProgressBar to mCRunner's progress
	 *             property, binds the valueOfPi variable to mCRunner's value
	 *             property creates a new thread, makes this thread a Daemon
	 *             thread, then runs this thread.
	 */
	public void simulateClicked() throws Exception {
		this.reset();
		try {
			//TODO add threadcap
			setNumTrialsClicked();
			setNumThreadsClicked();
			System.out.println("n: " + numThreads);
			if (numThreads < 1) {
				this.inputError = true;
				if(threadStatusLabel.getText().equals("")){
					threadStatusLabel.setText("The number of threads must be at least one.");
					threadStatusLabel.setFill(Color.FIREBRICK);
				}
				else{
					threadStatusLabel.setText("The number of threads must be a whole number, and at least one.");
					threadStatusLabel.setFill(Color.FIREBRICK);
				}
			}
			if(numThreads > 1000){
				this.inputError = true;
				if(threadStatusLabel.getText() == ""){
					threadStatusLabel.setText("The number of threads must be less than 1000.");
					threadStatusLabel.setFill(Color.FIREBRICK);
				}
				else{
					threadStatusLabel.setText("The number of threads must be a whole number, and be less than 1000.");
					threadStatusLabel.setFill(Color.FIREBRICK);
				}
			}
			if (numTrials < 10) {
				this.inputError = true;
				if(trialStatusLabel.getText() == ""){
					trialStatusLabel.setText("The number of trials must be at least ten.");
					trialStatusLabel.setFill(Color.FIREBRICK);
				}
				else{
					trialStatusLabel.setText("The number of trials must be a whole number, and at least ten.");
					threadStatusLabel.setFill(Color.FIREBRICK);
				}
			}
			if(inputError == true){
				throw new NumberFormatException();
			}
		} 
		catch (NumberFormatException e) {
		statusLabel.setText("Failed");
		statusLabel.setFill(Color.FIREBRICK);
		return;
	}
		statusLabel.setFill(Color.GREEN);
		MCRunnerNoAWS mCRunner = new MCRunnerNoAWS(numThreads, numTrials, 4, "buffinRoll");
		cancelBtn.setOnAction(e -> mCRunner.cancel());
		cancelBtn.disabledProperty().and(mCRunner.stateProperty().isNotEqualTo(RUNNING));
		valueOfPi.textProperty().bind(mCRunner.valueProperty().asString());
		piProgressBar.progressProperty().bind(mCRunner.progressProperty());
		Thread workingThread = new Thread(mCRunner);
		workingThread.setDaemon(true);
		workingThread.start();
		statusLabel.textProperty().bind(mCRunner.stateProperty().asString());
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