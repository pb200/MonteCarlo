package ca.blakey.monte_carlo.model;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class DiceMeasureTest {
	
	@Test
	public void test() throws NoSuchAlgorithmException, InterruptedException {
		DiceRoll diceRoll = new DiceRoll();
		double [] arrayInput = {0.1};
	assertEquals(diceRoll.Call(arrayInput), 1, 0.0);
		
		

	}

}
