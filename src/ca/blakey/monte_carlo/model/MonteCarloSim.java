package ca.blakey.monte_carlo.model;

//Copyright (c) <2015> <Phillip Blakey>
import java.util.Timer;

/*
 * This class
 */
public class MonteCarloSim implements Runnable {
	static Timer timer = new Timer();
	static int seconds = 0;
	private long seed = 0;
	private int trials = 0;
	private int numVars = 0;
	private double totalExecutionTime = 0;
	private double[] standardDeviation;
	private double[] varience;
	private double[] mean;
	private double endTime = 0;
	private double successes = 0;

	public MonteCarloSim(long seedIn, int trialsIn, int numVarsIn) {
		this.seed = seedIn;
		this.trials = trialsIn;
		this.numVars = numVarsIn;
		standardDeviation = new double[numVarsIn];
		varience = new double[numVarsIn];
		mean = new double[numVarsIn];
	}
	public double[] getStdArray() {
		return this.standardDeviation;
	}

	public double[] getMeanArray() {
		return this.mean;
	}

	public double[] getVarienceArray() {
		return this.varience;
	}

	public double getEndTime() {
		return this.endTime;
	}

	public double getExcecutionTime() {
		return this.totalExecutionTime;
	}

	public double getStandardDeviation(int variableSD) {
		return this.standardDeviation[variableSD];
	}

	public double getVarience(int variableV) {
		return this.varience[variableV];
	}

	public double getMean(int variableM) {
		return this.mean[variableM];
	}

	public double getSuccesses() {
		return this.successes;
	}

	public int getNumTrials() {
		return this.trials;
	}

	public void run() {
		{
			try {
				Thread.sleep(1000); // TODO (phil) understand this
			} catch (InterruptedException ie) {
				System.err.println("Interrupted");
				return;
			}
			System.out.println("Thread ID " + Thread.currentThread().getId() + " running");
			TrialRunner tRunner = new TrialRunner(this.trials, this.numVars, this.seed);
			Measure nVarF;
			if (true){
				nVarF = new DiceRoll();
			}
			else{
				nVarF = new NVarFunction();
			}
			final long startTime = System.currentTimeMillis();
			tRunner.runNVar(nVarF);
			for (int j = 0; j < tRunner.getNumVars(); j++) {
				tRunner.getTrialStat(j).calculuateStdDev();
				tRunner.getTrialStat(j).calculateVariance();
				tRunner.getTrialStat(j).calculateMean();
				this.standardDeviation[j] = tRunner.getTrialStat(j).getStandardDev();
				this.varience[j] = tRunner.getTrialStat(j).getVariance();
				this.mean[j] = tRunner.getTrialStat(j).getMean();
			}
			final long endTime = System.currentTimeMillis();
			this.totalExecutionTime = endTime - startTime;
			this.endTime = endTime;
			this.successes = tRunner.getSuccesses();

		}
	}
}
