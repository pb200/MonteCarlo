package ca.blakey.monte_carlo.model;


/**
 * @author phill_000
 *Copyright (c) <2015> <Phillip Blakey>
 *
 *The Class has two methods, Call, and PostCall. Call takes in an input of random variables
 *(for this simulation the input will always be length two), 
 * with values between 0 and 1, in this case each representing one axis of the cartesian plane.
 * Call then scales these random numbers to be between -1 and 1 creating a 2 by 2 square. Call
 * Then tests to see if the point given by these two random variables is in the unit circle by
 * checking to see if the Pythagorean norm is less than or equal to one. If the point is in
 * the unit circle Call returns one, otherwise it returns 0.
 * 
 * PostCall takes an input(the sum of all the Call returns divided by the number of trials times
 * threads) which is the probability of being inside the unit circle and multiplies
 * it by four. This is because the formula for pi is 
 * 4*Probability of being inside the circle. PostCall then returns this value.
 *
 */
public class PiRoll extends Measure{
	/* (non-Javadoc)
	 * @see ca.blakey.monte_carlo.model.Measure#Call(double[])
	 */
	@Override
	public double Call(double [] input){
		double y = 2*input[1]-1;	
		double x = 2*input[0]-1;
		if (Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2))<=1){
			return 1.0;
		}
		else {
			return 0.0;
		}
		
	}
	/* (non-Javadoc)
	 * @see ca.blakey.monte_carlo.model.Measure#postCall(double)
	 */
	@Override
	double postCall(double input) {
		return 4*input;
	}




}
