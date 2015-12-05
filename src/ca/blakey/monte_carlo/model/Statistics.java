package ca.blakey.monte_carlo.model;


/**
 * @author phill_000
 *Copyright (c) <2015> <Phillip Blakey>
 *
 * This class calculates maxValue, minValue, mean, standard deviation, and Variance
 * for a given set of inputs.
 * The way this class works is an instance is created then for each input the method 
 * run is called with the input value as an input. The number of inputs, sum of inputs, 
 * sum of squares, max value, and min value of inputs are then saved as private variables. 
 * When the methods to get the statistical values are called the methods for calculating 
 * standard deviation, mean, and variance are called inside these methods.
 * This is done so that these
 * statistical values can be updated at any point however the processing power to actually
 * calculate the values is only used when the values need to be calculated rather than after
 * every input.
 * 
 *
 */
public class Statistics {
	private double maxValue = 0x0.0000000000001P-1022;
	private double minValue = 0x1.fffffffffffffP+1023;
	private double mean = 0;
	private double standardDev = 0;
	private double variance = 0;
	private double totalPower0 = 0;
	private double totalPower1 = 0;
	private double totalPower2 = 0;

	/**
	 * @param trialValue
	 *            This parameter goes into updating the number of inputs, sum of
	 *            inputs, sum of squares, max value, and min value.
	 */
	public void run(double trialValue) {
		this.totalPower0++;
		this.totalPower2 = this.totalPower2 + Math.pow(trialValue, 2);
		this.totalPower1 = this.totalPower1 + trialValue;

		if (trialValue > this.maxValue) {
			this.maxValue = trialValue;
		}
		if (trialValue < minValue) {
			this.minValue = trialValue;
		}

	}

	/**
	 * @return Returns the number of inputs.
	 */
	public double getTotalPower0() {
		return this.totalPower0;
	}

	/**
	 * This method calculates standard deviation.
	 */
	public void calculuateStdDev() {
		this.standardDev = 1 / (double) (totalPower0) * Math.sqrt(totalPower0 * totalPower2 - Math.pow(totalPower1, 2));
	}

	/**
	 * 
	 */
	public void calculateVariance() {
		this.variance = Math.pow(this.standardDev, 2);
	}

	/**
	 * This method calculates variance.
	 */
	public void calculateMean() {
		this.mean = totalPower1 / totalPower0;
	}

	/**
	 * @return This method returns the maximum input recorded.
	 */
	public double getMaxValue() {
		return this.maxValue;
	}

	/**
	 * @return This method returns the minimum input recorded.
	 */
	public double getMinValue() {
		return this.minValue;
	}

	/**
	 * @return This method returns the mean of the inputs.
	 */
	public double getMean() {
		this.calculateMean();
		return this.mean;
	}

	/**
	 * @return This method returns the standard deviation of the inputs
	 */
	public double getStandardDev() {
		this.calculuateStdDev();
		return this.standardDev;
	}

	/**
	 * @return This method returns the sum of all the inputs.
	 */
	public double getSum() {
		return this.totalPower1;
	}

	/**
	 * @return This method returns the variance of the inputs.
	 */
	public double getVariance() {
		this.calculateVariance();
		return this.variance;
	}
	/*
	 * 
	 * public String toString() { String output = "Max: " + this.maxValue + "\n"
	 * + "Min: " + this.minValue + "\n" + "Mean: " + this.mean + "\n" +
	 * "Standard Deviation: " + this.standardDev + "\n" + "Sum: " +
	 * this.totalPower1 + "\n" + "Varience: " + this.variance + "\n" + "SS: " +
	 * this.totalPower2 + "\n"; return output; }
	 */

	/**
	 * @return THis method returns the sum of all the inputs.
	 */
	public double getTotalPower1() {
		return this.totalPower1;
	}

	/**
	 * @return This method returns the sum of squares of the inputs.
	 */
	public double getTotalPower2() {
		return this.totalPower2;
	}
}
