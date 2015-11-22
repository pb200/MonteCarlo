package ca.blakey.monte_carlo.model;

//Copyright (c) <2015> <Phillip Blakey>
import java.security.NoSuchAlgorithmException;


public class MCRunnerNoAWS {
	private int numThreads = 0;
	private int numVars = 0;
	private int numTrials = 0;
	private Statistics statistics;
	GenerateUUID seedArray;
	ResultStore resultStore;

	public MCRunnerNoAWS(int numThreadsIn, int numTrialsIn, int numVarsIn) throws NoSuchAlgorithmException {
		this.numThreads = numThreadsIn;
		this.numVars = numVarsIn;
		this.numTrials = numTrialsIn;
		seedArray = new GenerateUUID(numThreadsIn);
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
	public ResultStore getResultStore(){
		return this.resultStore;
	}

	public void runMC() throws NoSuchAlgorithmException, InterruptedException {
		Thread[] threads = new Thread[numThreads];
		MonteCarloSim[] mcs = new MonteCarloSim[numThreads];

		this.statistics = new Statistics();
		for (int i = 0; i < numThreads; i++) {
			mcs[i] = new MonteCarloSim(seedArray.getSeed(i), numTrials, 2);
			threads[i] = new Thread(mcs[i]);
			threads[i].start();


		}

		for (int i = 0; i < numThreads; i++) {
			threads[i].join();
		}
		this.resultStore = new ResultStore();
		for (int i = 0; i < numThreads; i++) { // TODO (phil) Check thread lifetimes
			for(int j = 0; j < numThreads; j++){
				this.statistics.run(mcs[j].getSuccesses());
			}

//			String std = ""+this.statistics.getStandardDev();
//			String mean = "" + this.statistics.getMean();
//			String variance = ""+this.statistics.getVariance();

// TODO (phil) Do a unit test to see the difference between toString and what I do in terms of length
			System.out.println("MCRunner Seed0!: " + seedArray.getSeed(0));
			System.out.println("MCRunner Seed: " + seedArray.getSeed(i));
			System.out.println("Excecution time: " + mcs[i].getExcecutionTime());
			System.out.println("End time: " + mcs[i].getEndTime());
			Result result = new Result(seedArray.getSeed(i), this.statistics.getMean(),
					this.statistics.getStandardDev(), this.statistics.getVariance(),
					mcs[i].getEndTime(), mcs[i].getExcecutionTime(),
					(int)mcs[i].getSuccesses(), mcs[i].getNumTrials());
			resultStore.Add(result);


		}
	}
}
