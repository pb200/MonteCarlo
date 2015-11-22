package ca.blakey.monte_carlo.model;

import java.util.UUID;
import java.security.NoSuchAlgorithmException;

public class GenerateUUID {
	private long[] seed;

	public GenerateUUID(int numSeeds) throws NoSuchAlgorithmException {
		seed = new long[numSeeds];

		for (int i = 0; i < numSeeds; i++) {
			UUID tempID = UUID.randomUUID();
			seed[i] = tempID.getLeastSignificantBits();
		}
	}

	public long getSeed(int seedNum) {
		return this.seed[seedNum];
	}
}
