package com.foodbook.onlinemanager;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * Adapted from ESDemo by Chenlei Zhang
 * 
 * @see https://github.com/rayzhangcl/ESDemo
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes), and Chenlei Zhang
 *
 * @param <T> 
 */


public class ElasticSearchSearchResponse<T> {
	int took;
	boolean timed_out;
	transient Object _shards;
	Hits<T> hits;
	boolean exists;

	public Collection<ElasticSearchResponse<T>> getHits() {
		return hits.getHits();
	}

	public Collection<T> getSources() {
		Collection<T> out = new ArrayList<T>();
		for (ElasticSearchResponse<T> essrt : getHits()) {
			out.add(essrt.getSource());
		}
		return out;
	}

	public String toString() {
		return (super.toString() + ":" + took + "," + _shards + "," + exists + "," + hits);
	}
}