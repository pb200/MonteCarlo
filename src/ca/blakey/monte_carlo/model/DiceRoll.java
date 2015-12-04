package ca.blakey.monte_carlo.model;
//Copyright (c) <2015> <Phillip Blakey>
public class DiceRoll extends Measure{

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

	@Override
	double postCall(double input) {
		return input;
	}
}
