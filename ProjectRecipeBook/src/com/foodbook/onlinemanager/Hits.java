package com.foodbook.onlinemanager;

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


public class Hits<T> {
	int total;
	double max_score;
	Collection<ElasticSearchResponse<T>> hits;

	public Collection<ElasticSearchResponse<T>> getHits() {
		return hits;
	}

	public String toString() {
		return (super.toString() + "," + total + "," + max_score + "," + hits);
	}
}