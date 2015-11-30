package ca.blakey.monte_carlo.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrialRunnerTest {

	@Test
	public void trialRunnerCase1test() {
		int trialsIn=5;
		int numVarsIn=2;
		long seedIn = 1;
		TrialRunner trialRunner = new TrialRunner(trialsIn,numVarsIn,seedIn);
		PiRoll nVarF = new PiRoll();
		trialRunner.runNVar(nVarF);
		assertEquals(2.0,trialRunner.getNumVars(),  0.0);
		assertEquals(1.0,trialRunner.getSeed(), 0.0);
		assertEquals(2.0,trialRunner.getSuccesses(), 0.0);
		assertEquals(0.4,trialRunner.getSuccessP(), 0.0);
		assertEquals(5.0, trialRunner.getTrials(),0.0);
	}

}
