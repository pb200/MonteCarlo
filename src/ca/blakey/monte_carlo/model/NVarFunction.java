package ca.blakey.monte_carlo.model;

//Copyright (c) <2015> <Phillip Blakey>
public class NVarFunction {
	public double Check(double [] input){
		double y = 2*input[1]-1;	// The number is generated between 0-1 so this makes it between -1,1.
		double x = 2*input[0]-1;
		//System.out.println("x: "+x + " y: "+y);
		if (Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2))<=1){
			return 1.0;
		}
		else {
			return 0.0;
		}
	}
}
