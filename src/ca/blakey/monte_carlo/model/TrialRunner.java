package ca.blakey.monte_carlo.model;

//Copyright (c) <2015> <Phillip Blakey>
public class TrialRunner {
	private int trials = 0;
	private double successes = 0;
	private double pSuccess = 0;
	private int numVars;
	private long seed;
	private Statistics[] trialStats;

	public TrialRunner(int trialsIn, int numVarsIn, long seedIn) {
		this.trials = trialsIn;
		this.numVars = numVarsIn;
		this.seed = seedIn;
		if (numVarsIn > 1) {
			trialStats = new Statistics[this.getNumVars()];
			for (int j = 0; j < this.getNumVars(); j++) {
				trialStats[j] = new Statistics();
			}
		}
	}

	public Statistics getTrialStat(int index) {
		return this.trialStats[index];
	}

	public int getNumVars() {
		return this.numVars;
	}

	public long getSeed() {
		return this.seed;
	}

	public int getTrials() {
		return this.trials;
	}

	public double getSuccesses() {
		return this.successes;
	}

	public void incrementSuccesses(double input) { // TODO (phil) change to sum
		this.successes = successes + input;
	}

	public void setProbabilities() {
		this.pSuccess = ((double) (this.successes) / (double) (this.trials));
	}
	public double getSuccessP() {
		return this.pSuccess;
	}

	/*
	 * This method generates a random number using the randomnumgen class, takes
	 * in an instance of oneVarFunction-oneVarFunction takes in a random number
	 * and returns a value-puts the random number into an instance of the
	 * statistics class then puts the random number into the instance of
	 * OneVariableFunction.
	 *
	 */

	public void runOneVar(OneVarFunction oneVarF) {
		Statistics trial = new Statistics();
		RandomNumGen numGen = new RandomNumGen(this.getSeed());
		for (int i = 1; i < this.getTrials() + 1; i++) {
			// TODO (phil) Calculate statistics on output value
			double randomVar = numGen.getRandomNum();
			trial.run(randomVar);
			double oneVarValue = oneVarF.Check(randomVar);
			this.incrementSuccesses(oneVarValue);
		}
		trial.calculuateStdDev();
		trial.calculateVariance();
		trial.calculateMean();
		System.out.println(trial);
		System.out.println(this);
	}

	public void runNVar(NVarFunction nVarF) {
		// TODO (phil) Calculate statistics on output value
		RandomNumGen numGen = new RandomNumGen(this.getSeed());
		double[] randomVars = new double[this.getNumVars()];
		for (int i = 1; i < this.getTrials() + 1; i++) {
			for (int j = 0; j < this.getNumVars(); j++) {
				double randomVar = numGen.getRandomNum();
				randomVars[j] = randomVar;
				this.trialStats[j].run(randomVars[j]);
			}
			double nVarValue = nVarF.Check(randomVars);
			this.incrementSuccesses(nVarValue);

		}

		this.setProbabilities();
	}
}
