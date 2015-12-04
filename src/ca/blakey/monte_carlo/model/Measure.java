package ca.blakey.monte_carlo.model;
//Copyright (c) <2015> <Phillip Blakey>
abstract class Measure {
	abstract double Call(double [] input);
	abstract double postCall(double input);
}
