package ca.blakey.monte_carlo.model;

abstract class Measure {
	abstract double Call(double [] input);
	abstract double postCall(double input);
}
