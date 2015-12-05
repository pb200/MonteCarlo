package ca.blakey.monte_carlo.model;


/**
 * @author phill_000
 *Copyright (c) <2015> <Phillip Blakey>
 *
 * This abstract class is extended in the part of a simulation that takes in
 * N random variables and returns a metric, for example DiceRoll, and PiRoll.
 *
 */
abstract class Measure {
	abstract double Call(double [] input);
	abstract double postCall(double input);
}
