package ca.blakey.monte_carlo.model;

public class Result {
	private long seed;
	private double mean;
	private double std;
	private double variance;
	private double time;
	private double excecutionTime;
	private int successes;
	private int trials;

	public Result(long seedIn, double meanIn, double stdIn,
			double varianceIn, double timeIn, double excecutionTimeIn,
			int successesIn, int trialsIn){

		this.seed = seedIn;
		this.mean = meanIn;
		this.std = stdIn;
		this.variance = varianceIn;
		this.time = timeIn;
		this.excecutionTime = excecutionTimeIn;
		this.successes = successesIn;
		this.trials = trialsIn;
	}
	public Result(){
		this.seed = 2;
		this.mean =2;
		this.std = 2;
		this.variance = 0;
		this.time = 0;
		this.excecutionTime = 0;
		this.successes = 0;
		this.trials = 0;
	}

	public long getSeed(){
		return this.seed;
	}

	public double getMean() {
		return this.mean;
	}

	public double getStd() {
		return this.std;
	}
	public double getVarience(){
		return this.variance;
	}
	public double getTime(){
		return this.time;
	}
	public double getExcecutionTime(){
		return this.excecutionTime;
	}
	public double getSuccesses(){
		return this.successes;
	}
	public double getTrials(){
		return this.trials;
	}

	public void setSeed(long seedIn){
		this.seed = seedIn;
	}

	public void setMean(double meanIn){
		this.mean = meanIn;
	}

	public void setStd(double stdIn){
		this.std = stdIn;
	}



}
