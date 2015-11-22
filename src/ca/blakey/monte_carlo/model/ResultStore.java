package ca.blakey.monte_carlo.model;

import java.util.Hashtable;

public class ResultStore {

	private long count_;
	private Hashtable<Long, Result> map = new Hashtable<Long, Result>();

	public long count() {
		// TODO Auto-generated method stub
		return count_;
	}

	public void Add( Result result1) {

		map.put(result1.getSeed(), result1);
		++(this.count_);

	}

	public Result Get(long seed) {
		return this.map.get(seed);
	}

}
