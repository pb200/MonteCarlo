package ca.blakey.monte_carlo.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatisticsTest {

	@Test
	public void test() {
		Statistics statistics = new Statistics();
		statistics.run(1.0);
		statistics.run(1.0);
		assertEquals(2.0, statistics.getTotalPower0(), 0.0);
		assertEquals(2.0, statistics.getTotalPower1(), 0.0);
		assertEquals(2.0, statistics.getTotalPower2(), 0.0);
		assertEquals(0.0, statistics.getStandardDev(), 0.0);
		assertEquals(0.0, statistics.getVariance(), 0.0);
		assertEquals(1.0, statistics.getMean(), 0.0);
		assertEquals(1.0, statistics.getMaxValue(), 0.0);
		assertEquals(1.0,statistics.getMinValue(), 0.0);
	}

	@Test
	public void differentInputtest() {
		Statistics statistics = new Statistics();
		statistics.run(2.0);
		statistics.run(1.0);
		assertEquals(2.0, statistics.getTotalPower0(), 0.0);
		assertEquals(3.0, statistics.getTotalPower1(), 0.0);
		assertEquals(5.0, statistics.getTotalPower2(), 0.0);
		assertEquals(0.5, statistics.getStandardDev(), 0.0);
		assertEquals(0.25, statistics.getVariance(), 0.0);
		assertEquals(1.5, statistics.getMean(), 0.0);
		assertEquals(2.0, statistics.getMaxValue(), 0.0);
		assertEquals(1.0,statistics.getMinValue(), 0.0);
	}


}
