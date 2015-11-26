package ca.blakey.monte_carlo.model;

import java.awt.Button;
import java.security.NoSuchAlgorithmException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Controller {
	private int numThreads = 0;
	private int numTrials = 0;
	private double pi = 0;
	@FXML private Button buttonSetThreads;
	@FXML private TextField numThreadsInput;
	@FXML private Button buttonSetTrials;
	@FXML private TextField numTrialsInput;
	@FXML private Button Simulate;
	@FXML private Text currentNumThreads;
	@FXML private Text currentNumTrials;
	@FXML private Text valueOfPi;
	@FXML private VBox PiVBox;
	@FXML private VBox DiceVBox;
	@FXML private Text StatusLabel;
	private int numTimesPiVBoxVisibleClicked = 0;
	private int numTimesDiceVBoxVisibleClicked = 0;

	public void setPiSimVisible(){
		this.numTimesDiceVBoxVisibleClicked++;
		DiceVBox.setVisible(false);
		numTimesPiVBoxVisibleClicked++;
		if (this.numTimesPiVBoxVisibleClicked % 2 == 0)
			PiVBox.setVisible(false);
		else
			PiVBox.setVisible(true);
	}
	public void setDiceSimVisible(){
		numTimesPiVBoxVisibleClicked++;
		PiVBox.setVisible(false);
		numTimesDiceVBoxVisibleClicked++;
		if(this.numTimesDiceVBoxVisibleClicked %2 == 0){
			DiceVBox.setVisible(true);
		}
		else{
			DiceVBox.setVisible(true);
		}
	}
	public void setNumThreadsClicked(){
		try{
		numThreads = Integer.parseInt(numThreadsInput.getText());
		currentNumThreads.setText(numThreadsInput.getText());
		}
		catch(NumberFormatException e){
			 StatusLabel.setText("Please enter an integer");
			 System.out.println("You did not enter an integer");
		}
		System.out.println(numThreads);
	}
	public void setNumTrialsClicked(){
		try{
		numTrials = Integer.parseInt(numTrialsInput.getText());
		currentNumTrials.setText(numTrialsInput.getText());
		System.out.println(numTrials);
		}
		catch(NumberFormatException e){
			 StatusLabel.setText("Please enter an integer");
			 StatusLabel.setFill(Color.FIREBRICK);
			 System.out.println("You did not enter an integer");
		}
	}
	public void simulateClicked() throws NoSuchAlgorithmException, InterruptedException{
		pi = 0;
		valueOfPi.setText(null);
		double successes =0;
		/*
		MCRunner mCRunner = new MCRunner(numThreads, numTrials, 2); // TODO (phil) Create class constant for num variables
		ScanStatistics scanStatistics = new ScanStatistics();
		mCRunner.runMC();
		 StatusLabel.setText("Running");
		for(int i = 0; i <mCRunner.getNumThreads(); i++){
			long seed = mCRunner.getSeedArray().getSeed(i);
			double tempSuccesses =scanStatistics.getSuccesses(seed);
			successes = successes + (tempSuccesses);
		}
		*/
		MCRunnerNoAWS mCRunner= new MCRunnerNoAWS(numThreads, numTrials, 50);

		mCRunner.runMC();

		ResultStore resultStore = mCRunner.getResultStore();
		for(int i = 0; i <mCRunner.getNumThreads(); i++){
			long seed = mCRunner.getSeedArray().getSeed(i);
			Result result = resultStore.Get(seed);

			double tempSuccesses = result.getSuccesses();
			successes = successes + (tempSuccesses);
		}

		pi = 4*(successes)/(numTrials*numThreads);
		double sum = successes/(numTrials*numThreads);
		valueOfPi.setText(Double.toString(sum));
		 StatusLabel.setText("Finished");

	}
}

