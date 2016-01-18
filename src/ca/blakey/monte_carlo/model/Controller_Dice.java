package ca.blakey.monte_carlo.model;


//import java.awt.Button;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import static javafx.concurrent.Worker.State.RUNNING;
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
	private long numTrials = 0;
	private int numDice = 0;
	
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
    @FXML
    private Button cancelBtn;
    @FXML
    private Text diceStatusLabelDice;
    @FXML
    private Text trialStatusLabelDice;
    @FXML
    private Text threadStatusLabelDice;
    @FXML
    private Text standardDeviationValue;


	private boolean inputError = false;

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
		this.reset();
		numThreadsInDice.setText("4");
		numTrialsInDice.setText("100000");
		numDiceInDice.setText("2");
		myController.setScreen(Main.mainPageName);
	}

	/**
	 * This method is run when the simulate button is clicked. This method takes 
	 * an integer from the text box, Number of Threads, and sets the private variable
	 * numThreads to this value. Should the input text in the text box be something
	 * other than an integer this method writes "Please enter an integer" on the GUI.
	 */
	public void setNumThreadsClickedDice() {
		String numThreadsString = numThreadsInDice.getText();
			if(Double.parseDouble(numThreadsString) %1 != 0){
			
				this.inputError = true;
				threadStatusLabelDice.setText("The number of threads must be a whole number.");
				threadStatusLabelDice.setFill(Color.FIREBRICK);
			}
			numThreads = (int) Double.parseDouble(numThreadsString);
	}

	/**
	 * This method is run when the simulate button is clicked. This method takes 
	 * an integer from the text box, Number of Trials, and sets the private variable
	 * numTrials to this value. Should the input text in the text box be something
	 * other than an integer this method writes "Please enter an integer" on the GUI.
	 */
	public void setNumTrialsClickedDice() {
		String numTrialsString = numTrialsInDice.getText();
			if(Double.parseDouble(numTrialsString) %1 != 0){
				this.inputError = true;
				trialStatusLabelDice.setText("The number of trials must be a whole number.");
				trialStatusLabelDice.setFill(Color.FIREBRICK);
			}
			numTrials = (long) Double.parseDouble(numTrialsString);
			System.out.println(numTrials);
	}

	/**
	 * This method is run when the simulate button is clicked. This method takes 
	 * an integer from the text box, Number of Dice, and sets the private variable
	 * numDice to this value. Should the input text in the text box be something
	 * other than an integer this method writes "Please enter an integer" on the GUI.
	 */
	public void setNumDiceClickedDice() {
		String numDiceString = numDiceInDice.getText();
			if(Double.parseDouble(numDiceString) %1 != 0){
			
				this.inputError = true;
				diceStatusLabelDice.setText("The number of dice must be a whole number.");
				diceStatusLabelDice.setFill(Color.FIREBRICK);
			}
			numDice = (int) Double.parseDouble(numDiceString);
			}

	public void descriptionDialogClicked() {
		Alert alert = new Alert(AlertType.INFORMATION);
        String titleTxt="Dice simulation";
		alert.setTitle(titleTxt);
		alert.setHeaderText("dice simulation");
		String s = " This dice simulation uses the array of randomly generated values to return a "
				+ "value on a six sided dice in the call method. It then will calculate the average"
				+ " sum of the the dice. A random number between 0 and 1 will be scaled up to a number "
				+ "from 1 to 6.  The average sum of the dice is calculated every time a thread is "
				+ "initialized.";
		alert.setContentText(s);
		alert.show();

	}
	public void reset(){
		inputError = false;
		if(statusLabelDice.textProperty().isBound() == true){
		statusLabelDice.textProperty().unbind();
		avgSum.textProperty().unbind();
		diceProgressBar.progressProperty().unbind();
		standardDeviationValue.textProperty().unbind();
		}
		statusLabelDice.setText("");
		avgSum.setText("");
		diceProgressBar.setProgress(0.0);
		trialStatusLabelDice.setText("");
		threadStatusLabelDice.setText("");
		diceStatusLabelDice.setText("");
		standardDeviationValue.setText("");
		
		
		
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
		this.reset();
		try {
			inputError = false;
			setNumTrialsClickedDice();
			setNumThreadsClickedDice();
			setNumDiceClickedDice();
		
			if(numThreads <1){
			
				this.inputError = true;
				if(threadStatusLabelDice.getText() == ""){
					threadStatusLabelDice.setText("The number of threads must be at least one.");
					threadStatusLabelDice.setFill(Color.FIREBRICK);
				
				}
				else{
					threadStatusLabelDice.setText("The number of threads must be a whole number, and at least one.");
					threadStatusLabelDice.setFill(Color.FIREBRICK);
				}
			}
			if(numThreads > 1000){
				if(threadStatusLabelDice.getText() == ""){
				this.inputError = true;
				threadStatusLabelDice.setText("The number of threads must be at less than 1000.");
				threadStatusLabelDice.setFill(Color.FIREBRICK);
			
			}
			else{
				threadStatusLabelDice.setText("The number of threads must be a whole number, and less than 1000.");
				threadStatusLabelDice.setFill(Color.FIREBRICK);
			}
			}
			if(numTrials <10){
	
				this.inputError = true;
				if(trialStatusLabelDice.getText() == ""){
					trialStatusLabelDice.setText("The number of trials must be at least ten.");
					trialStatusLabelDice.setFill(Color.FIREBRICK);
				}
				else{
					trialStatusLabelDice.setText("The number of trials must be a whole number, and at least ten.");
				}
			}
			if(numDice <1){
				this.inputError = true;
				if(diceStatusLabelDice.getText() == ""){
					diceStatusLabelDice.setText("The number of dice must be at least one.");
					diceStatusLabelDice.setFill(Color.FIREBRICK);
				}
				else{
					diceStatusLabelDice.setText("The number of dice must be a whole number, and at least one.");
				}
			}
			if(inputError == true){
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			statusLabelDice.setText("Failed");
			statusLabelDice.setFill(Color.FIREBRICK);
			return;
		}
		statusLabelDice.setFill(Color.GREEN);
		MCRunnerNoAWS mCRunner = new MCRunnerNoAWS(numThreads, numTrials, numDice, "diceRoll");
		cancelBtn.setOnAction(e -> mCRunner.cancel());
		cancelBtn.disabledProperty().and(mCRunner.stateProperty().isNotEqualTo(RUNNING));
		diceProgressBar.progressProperty().bind(mCRunner.progressProperty());
		avgSum.textProperty().bind(mCRunner.valueProperty().asString());
		standardDeviationValue.textProperty().bind((mCRunner.standardDevProperty.asString()));
		Thread workingThread = new Thread(mCRunner);
		workingThread.setDaemon(true);
		workingThread.start();
		statusLabelDice.textProperty().bind(mCRunner.stateProperty().asString());
		
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
