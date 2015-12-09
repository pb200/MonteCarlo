package ca.blakey.monte_carlo;
import java.security.NoSuchAlgorithmException;

import ca.blakey.monte_carlo.model.GenerateUUID;
import ca.blakey.monte_carlo.model.MonteCarloSim;
public class MonteCarloSimTimeTest {
	public static void main(String[] args) throws NoSuchAlgorithmException{
		String times = "[";
		int numTrials = 1000;
		for(int i = 0;i<100; i++){
		GenerateUUID uuidGenerator = new GenerateUUID(1);
		long seed = uuidGenerator.getSeed(0);
		int numVars = 2;
		MonteCarloSim mcs = new MonteCarloSim(seed, numTrials, numVars, "diceRoll");
		mcs.run();
		times = times+mcs.getExcecutionTime()+", ";
		numTrials = numTrials + 1000;
		}
		System.out.println(times);
		
	}
		
}
