package ca.blakey.monte_carlo.model;

import java.util.Timer;

/**
 * @author phill_000 Copyright (c) <2015> <Phillip Blakey>
 *
 *         This class implements the runnable interface and runs the simulation
 *         selected in the GUI. The run method is described below. The run
 *         method does all the work of the class as all other methods are
 *         setters or getters. The run method is an implementation of the run
 *         method in Runnable. This method simply generates an array of random
 *         variables with length equal to the number of random variables, for
 *         each random variables runs some statistics, then and calls the nVarF,
 *         an instance of nVariable function, and increments the sum by the
 *         returned value numTrials times. This method also times how long it
 *         takes to do the procedure mentioned above and saves the resulting
 *         time in a private variable.
 *
 */
public class MonteCarloSim implements Runnable {
	static Timer timer = new Timer();
	static int seconds = 0;
	private long seed = 0;
	private long numTrials = 0;
	private int numVars = 0;
	private double totalExecutionTime = 0.0;
	private double[] standardDeviation;
	private double[] varience;
	private double[] mean;
	private double endTime = 0;
	private double successes = 0;
	private String simulationType;
	private Measure nVarF;
	private Statistics[] trialStats;
	private double sum = 0;
	private RandomNumGen numGen;

	/**
	 * @param seedIn
	 *            This parameter is the seed for all random numbers generated in
	 *            this class.
	 * @param trialsIn
	 *            This parameter is the number of for which this part of the
	 *            simulation will be run.
	 * @param numVarsIn
	 *            This parameter gives the number of random variables to be
	 *            used.
	 * @param simulationTypeIn
	 *            This parameters indicates which simulation will be run
	 * 
	 *            The constructor sets the private variables of the class to the
	 *            input paramters listed above.
	 */
	public MonteCarloSim(long seedIn, long trialsIn, int numVarsIn, String simulationTypeIn) {

		this.seed = seedIn;
		this.simulationType = simulationTypeIn;
		this.numTrials = trialsIn;
		this.numVars = numVarsIn;
		standardDeviation = new double[numVarsIn];
		varience = new double[numVarsIn];
		mean = new double[numVarsIn];
		numGen = new RandomNumGen(this.seed);
		if (simulationType.compareTo("diceRoll") == 0) {
			nVarF = new DiceRoll();
		} else {
			nVarF = new PiRoll();
		}
		trialStats = new Statistics[(int) numVars];
		for (int j = 0; j < numVars; j++) {
			trialStats[j] = new Statistics();
		}
	}

	/*
	 * public double[] getStdArray() { return this.standardDeviation; }
	 * 
	 * public double[] getMeanArray() { return this.mean; }
	 * 
	 * public double[] getVarienceArray() { return this.varience; }
	 */
	/**
	 * @return This method returns the time the run method ends (The run method
	 *         is below).
	 */
	public double getEndTime() {
		return this.endTime;
	}

	/**
	 * @return This method returns the time it takes for the run method to run.
	 */
	public double getExcecutionTime() {
		return this.totalExecutionTime;
	}

	/*
	 * public double getStandardDeviation(int variableSD) { return
	 * this.standardDeviation[variableSD]; }
	 * 
	 * public double getVarience(int variableV) { return
	 * this.varience[variableV]; }
	 * 
	 * public double getMean(int variableM) { return this.mean[variableM]; }
	 */
	/**
	 * @return This method returns the the value that the simulation has
	 *         calculated, for example Pi for the pi simulation.
	 */
	public double getSuccesses() {
		return this.successes;
	}

	/**
	 * @return This method returns the number of trials that the simulation was
	 *         run for.
	 * 
	 */
	public long getNumTrials() {
		return this.numTrials;
	}

	/**
	 * @param input
	 *            This parameter is the metric returned by the nVarFunction.
	 * 
	 *            This method increments the sum of all returns for each trial
	 *            of the simulation.
	 */
	private void incrementSum(double input) {
		this.sum = sum + input;
	}

	// See description at the beginning of the class.
	// This is what is called by MCRunner for each thread.
	public void run() {
		{

			System.out.println("Thread ID " + Thread.currentThread().getId() + " running");
		
			double[] randomVars = new double[(int)this.numVars];
			final long startTime = System.nanoTime();

			for (int i = 1; i < this.numTrials + 1; i++) {
				for (int j = 0; j < this.numVars; j++) {
					double randomVar = numGen.getRandomNum();
					randomVars[j] = randomVar;
					this.trialStats[j].run(randomVars[j]);
				}
				double nVarValue = nVarF.Call(randomVars);
				this.incrementSum(nVarValue);
			}

			final long endTime = System.nanoTime();
			this.totalExecutionTime = endTime - startTime;
			System.out.println(startTime +", " + endTime);
			this.endTime = endTime;
			this.successes = nVarF.postCall(this.sum);
		}
	}
}
