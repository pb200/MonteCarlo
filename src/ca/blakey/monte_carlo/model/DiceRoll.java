package ca.blakey.monte_carlo.model;

/**
 * @author phill_000
 *Copyright (c) <2015> <Phillip Blakey>
 *
 * The Class has two methods, Call, and PostCall. Call takes in an input of random variables, 
 * with values between 0 and 1, in this case each representing one die. For each random 
 * variable if statements check what value of the die was rolled. This is done by breaking
 * up the interval 0 to 1 into 6 smaller intervals each representing one face of the die.
 * The PostCall method takes in an input, the average sum of the die, and returns that input
 * as an output.
 *
 */
public class DiceRoll extends Measure{

	/* (non-Javadoc)
	 * @see ca.blakey.monte_carlo.model.Measure#Call(double[])
	 */
	@Override
	double Call(double[] input) {
		int [] diceValues = new int[input.length];
		int sum = 0;
		
		for(int i = 0; i < input.length; i++){
		if (input[i] <0.16667){
			diceValues[i] = 1;
		}
		else if(input[i] <0.3334){
			diceValues[i] = 2;
		}
		else if(input[i] <= 0.5){
			diceValues[i] = 3;
		}
		else if(input[i] < 0.6667){
			diceValues[i] = 4;
		}
		else if(input[i] < 0.83334){
			diceValues[i] = 5;
		}
		else if(input[i]<1){
			diceValues[i] = 6;
		}
		sum = sum+ diceValues[i];
		}
		return sum;
	}

	/* (non-Javadoc)
	 * @see ca.blakey.monte_carlo.model.Measure#postCall(double)
	 */
	@Override
	double postCall(double input) {
		return input;
	}
}
