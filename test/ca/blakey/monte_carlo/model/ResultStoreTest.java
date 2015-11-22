package ca.blakey.monte_carlo.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultStoreTest {

	@Test
	public void ResultStoreConstructEmptyTest() {
		ResultStore store = new ResultStore();

		assertEquals(0, store.count());
	}

	@Test
	public void ResultStoreAdd1Test() {
		ResultStore store = new ResultStore();

		Result result1 = new Result();

		store.Add(result1);

		assertEquals(1, store.count());
	}

	@Test
	public void ResultStoreGet1Test() {
		ResultStore store = new ResultStore();

		Result result1 = new Result();

		store.Add(result1);
		Result gotResult = store.Get(result1.getSeed());

		assertNotEquals(0, gotResult.getSeed());
	}

	@Test
	public void MeanExistancetest() {
		Result result = new Result();
		assertEquals(2.0, result.getMean(), 0.0);
	}

	public class ResultTest {

		@Test
		public void test() {
			Result result = new Result();
			assertEquals(2.0, result.getStd(), 0.0);

		}

	}

}
