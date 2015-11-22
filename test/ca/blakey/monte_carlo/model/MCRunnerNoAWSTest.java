package ca.blakey.monte_carlo.model;
import ca.blakey.monte_carlo.model.*;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class MCRunnerNoAWSTest {

	@Test
	public void Basictest() {
		int numThreads = 1;
		int numTrials = 1;
		int numVariables = 2;
		MCRunnerNoAWS mCRunner;
		try {
			mCRunner = new MCRunnerNoAWS(numThreads, numTrials, numVariables);
			mCRunner.runMC();

			ResultStore resultStore = mCRunner.getResultStore();
			double successes = 0;
			for (int i = 0; i < mCRunner.getNumThreads(); i++) {
				long seed = mCRunner.getSeedArray().getSeed(i);
				Result result = resultStore.Get(seed);

				double tempSuccesses = result.getSuccesses();
				successes = successes + (tempSuccesses);
				assertTrue(  successes >= 0 );
			}
		} catch (NoSuchAlgorithmException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
