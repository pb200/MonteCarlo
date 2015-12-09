package ca.blakey.monte_carlo.model;


import java.security.NoSuchAlgorithmException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 * @author phill_000
 *Copyright (c) <2015> <Phillip Blakey>
 *
 * This class runs monteCarloSim over a number of threads decided by the user. After setting 
 * all of the input variables in the constructor the and a bunch of setters and getters
 * the method comes the method Call. This method is the workhorse of the class and is 
 * described below.
 * 
 *  The call method first creates an array of threads that is numThreads Long. It 
 * creates an array of monteCarloSims that is also numThreadsLong and creates an 
 * instance of the statistics class. The method then initializes the each MonteCarloSim.
 * The only special part of this initialization is that the number of trails for each 
 * simulation is  numTrials/10. The reason for doing this is to have 10 steps through
 * the simulation. Between each step the data on the gui is updated to keep the user 
 * informed about the progress of the simulation.
 * 
 * The Call method then starts all the threads and when they are finished joins them.
 * All the results of the simulation are then saved to an instance of ReslutStore
 * and the average value of PostCall from all
 * MonteCarloSims is found by sum = successes / (numTrials * numThreads)
 *
 */
public class MCRunnerNoAWS extends Task<ObservableList<Double>> {
	private int numThreads = 0;
	private int numVars = 0;
	private long numTrials = 0;
	private Statistics statistics;
	private String simType;
	GenerateUUID seedArray;
	ResultStore resultStore;

	/**
	 * @param numThreadsIn
	 * @param numTrialsIn
	 * @param numVarsIn
	 * @param simTypeIn
	 * @throws NoSuchAlgorithmException
	 */
	public MCRunnerNoAWS(int numThreadsIn, long numTrialsIn, int numVarsIn, String simTypeIn)
			throws NoSuchAlgorithmException {
		this.numThreads = numThreadsIn;
		this.numVars = numVarsIn;
		this.numTrials = numTrialsIn/numThreadsIn;
		seedArray = new GenerateUUID(numThreadsIn);
		this.simType = simTypeIn;
	}

	/**
	 * @return This method returns the number of threads that simulations were
	 *         run on.
	 */
	public long getNumThreads() {
		return this.numThreads;
	}

	/**
	 * @return This method returns the number of random variables.
	 */
	public long getNumVars() {
		return this.numVars;
	}

	/**
	 * @return This method returns the seedArray passed into monteCarloSim.
	 */
	public GenerateUUID getSeedArray() {
		return this.seedArray;
	}

	/**
	 * @return This method returns the class where all the results form
	 *         simulations are stored.
	 */
	public ResultStore getResultStore() {
		return this.resultStore;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.concurrent.Task#call()
	 */
	@Override
	protected ObservableList<Double> call() throws Exception {
		
		final ObservableList<Double> results = FXCollections.<Double> observableArrayList();

		Thread[] threads = new Thread[numThreads];
		MonteCarloSim[] mcs = new MonteCarloSim[(int) numThreads];
		this.statistics = new Statistics();
		int numSteps = 10;
		updateProgress(0, numSteps);
		long trialsPerStep = numTrials / 10;
		for (int i = 0; i < numThreads; i++) {
			mcs[i] = new MonteCarloSim(seedArray.getSeed(i), trialsPerStep, this.numVars, this.simType);
		}
		for (int stepNumber = 1; stepNumber < numSteps+1; stepNumber++) {
			for (int i = 0; i < numThreads; i++) {
				threads[i] = new Thread(mcs[i]);
				threads[i].start();

			}
			for (int i = 0; i < numThreads; i++) {
				threads[i].join();
			}
			this.resultStore = new ResultStore();
			for (int i = 0; i < numThreads; i++) {
				//for (int j = 0; j < numThreads; j++) {
				//	this.statistics.run(mcs[i].getSuccesses());
				//}

				System.out.println("MCRunner Seed0!: " + seedArray.getSeed(0));
				System.out.println("MCRunner Seed: " + seedArray.getSeed(i));
				System.out.println("Excecution time: " + mcs[i].getExcecutionTime());
				System.out.println("End time: " + mcs[i].getEndTime());
				Result result = new Result(seedArray.getSeed(i), this.statistics.getMean(),
						this.statistics.getStandardDev(), this.statistics.getVariance(), mcs[i].getEndTime(),
						mcs[i].getExcecutionTime(), (int) mcs[i].getSuccesses(), mcs[i].getNumTrials());
				resultStore.Add(result);

			}
			ResultStore resultStore = this.getResultStore();
			double successes = 0;
			for (int i = 0; i < this.getNumThreads(); i++) {
				long seed = this.getSeedArray().getSeed(i);
				Result result = resultStore.Get(seed);

				double tempSuccesses = result.getSuccesses();
				successes = successes + (tempSuccesses);
			}
			double sum = successes / (trialsPerStep*stepNumber * numThreads);

			results.add(sum);
			updateValue(FXCollections.<Double> unmodifiableObservableList(results));
			updateProgress(stepNumber + 1, numSteps);
		}
		return results;
	}

}
