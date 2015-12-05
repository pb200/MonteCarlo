package ca.blakey.monte_carlo.model;

import java.util.Hashtable;

/**
 * @author phill_000
 *Copyright (c) <2015> <Phillip Blakey>
 *
 * This class stores a hash table of Results.
 *
 */
public class ResultStore {

	private long count_;
	private Hashtable<Long, Result> map = new Hashtable<Long, Result>();

	/**
	 * @return 
	 */
	public long count() {
		// TODO Auto-generated method stub
		return count_;
	}

	/**
	 * @param result1 Is a parameter that gets added to the hash table.
	 */
	public void Add( Result result1) {

		map.put(result1.getSeed(), result1);
		++(this.count_);

	}

	/**
	 * @param seed Searches the hash table for a Result with seed, seed.
	 * @return Returns a Result with seed, seed.
	 */
	public Result Get(long seed) {
		return this.map.get(seed);
	}

}
