package ca.blakey.monte_carlo.model;

//Copyright (c) <2015> <Phillip Blakey>
import java.security.NoSuchAlgorithmException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

 

public class MCRunnerNoAWS extends Task<ObservableList<Double>> {
	private int numThreads = 0;
	private int numVars = 0;
	private int numTrials = 0;
	private Statistics statistics;
	private String simType;
	GenerateUUID seedArray;
	ResultStore resultStore;

	public MCRunnerNoAWS(int numThreadsIn, int numTrialsIn, int numVarsIn, String simTypeIn)
			throws NoSuchAlgorithmException {
		this.numThreads = numThreadsIn;
		this.numVars = numVarsIn;
		this.numTrials = numTrialsIn;
		seedArray = new GenerateUUID(numThreadsIn);
		this.simType = simTypeIn;
	}

	public int getNumThreads() {
		return this.numThreads;
	}

	public int getNumVars() {
		return this.numVars;
	}

	public GenerateUUID getSeedArray() {
		return this.seedArray;
	}

	public ResultStore getResultStore() {
		return this.resultStore;
	}

	@Override
	protected ObservableList<Double> call() throws Exception {
		final ObservableList<Double> results = FXCollections.<Double> observableArrayList();

		Thread[] threads = new Thread[numThreads];
		MonteCarloSim[] mcs = new MonteCarloSim[numThreads];
		this.statistics = new Statistics();
		int numSteps = 10;
		int trialsPerStep = numTrials/10;
		for (int i = 0; i < numThreads; i++) {
			mcs[i] = new MonteCarloSim(seedArray.getSeed(i), trialsPerStep, this.numVars, this.simType);
		}
		for (int stepNumber = 0; stepNumber < numSteps; stepNumber++) {
			for (int i = 0; i < numThreads; i++) {
				threads[i] = new Thread(mcs[i]);
				threads[i].start();
				
				// TODO find a way to read at each trialsPerStep number of Trials Completed.
				//if(mcs[i].getTrials() == trialsPerStep){
					
				//}
			}
			for (int i = 0; i < numThreads; i++) {
				threads[i].join();
			}
			this.resultStore = new ResultStore();
			for (int i = 0; i < numThreads; i++) { 
				for (int j = 0; j < numThreads; j++) {
					this.statistics.run(mcs[j].getSuccesses());
				}

			
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
			double sum = successes / (numTrials * numThreads);

			results.add(sum);
			updateValue(FXCollections.<Double> unmodifiableObservableList(results));
			updateProgress(stepNumber+1, numSteps);
		}
		return results;
	}

}
