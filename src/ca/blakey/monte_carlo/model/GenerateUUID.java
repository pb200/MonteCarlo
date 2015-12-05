package ca.blakey.monte_carlo.model;


import java.util.UUID;
import java.security.NoSuchAlgorithmException;

/**
 * @author phill_000
 *Copyright (c) <2015> <Phillip Blakey>
 *
 * This class creates a UUID that is used as a seed for generating random numbers
 * in MonteCarloSim.
 *
 */
public class GenerateUUID {
	private long[] seed;

	/**
	 * @param numSeeds This parameter gives the number of Seeds that need to be Generated.
	 * @throws NoSuchAlgorithmException
	 */
	public GenerateUUID(int numSeeds) throws NoSuchAlgorithmException {
		seed = new long[numSeeds];

		for (int i = 0; i < numSeeds; i++) {
			UUID tempID = UUID.randomUUID();
			seed[i] = tempID.getLeastSignificantBits();
		}
	}

	/**
	 * @param seedNum This parameter indicates which seed will be retrieved by the getter
	 * method below.
	 * @return getSeed returns a value of the seed array with index SeedNum.
	 */
	public long getSeed(int seedNum) {
		return this.seed[seedNum];
	}
}
