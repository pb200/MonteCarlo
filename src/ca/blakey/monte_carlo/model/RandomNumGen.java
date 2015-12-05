package ca.blakey.monte_carlo.model;


import java.util.Random;


/**
 * @author phill_000
 *Copyright (c) <2015> <Phillip Blakey>
 *
 * This class has one methods and a constructor. The constructor takes in a seed as an 
 * argument and creates a random number generator. The getRandomNum method uses this 
 * random number generator to return a new random number.
 *
 */
public class RandomNumGen {
	private long seed;
	private Random rand1;
	/**
	 * @param seedIn This parameter is used to create a random number generator.
	 */
	public RandomNumGen(Long seedIn){
		this.seed = seedIn;
		this.rand1 = new Random(this.seed);

	}


	/**
	 * @return Returns a random Double created by RandomNumGen.
	 */
	public double getRandomNum(){
		double randomNum = rand1.nextDouble();
		return randomNum;
	}

}
