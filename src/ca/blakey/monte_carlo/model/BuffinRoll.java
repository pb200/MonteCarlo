package ca.blakey.monte_carlo.model;

public class BuffinRoll extends Measure{


	@Override
	double Call(double[] input) {
		double x1 = input [0];
		double x2 = input [1];
		double y1 = input [2];
		double y2 = input [3];
		double dist;
		if ((Math.sqrt(x2*x2 + y2*y2) > 1) && !(x2==0 && y2==0)){
			 /*double x2temp=x2;
			 double y2temp=y2;
			 x2=x2-y2temp;
			 y2=y2-x2temp;*/
			 x2 =Math.sqrt(1-x2*x2);
			 y2= Math.sqrt(1-y2*y2);
			 
		}
		dist = Math.sqrt(x2*x2 + y2*y2);
		x2 /= dist;
		y2 /= dist;

		x2 += x1;
		y2 += y1;
		if (Math.round(x1) != Math.round(x2)){
		  	return 1.0;
			
		}
		else{
		  	return 0.0;
		  	}
	}

	@Override
	double postCall(double input) {
		// TODO Auto-generated method stub
		return input;
	}
}
