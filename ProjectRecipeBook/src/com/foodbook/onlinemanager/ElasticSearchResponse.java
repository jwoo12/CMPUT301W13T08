package com.foodbook.onlinemanager;

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


public class ElasticSearchResponse<T> {
	String _index;
	String _type;
	String _id;
	int _version;
	boolean exists;
	T _source;
	double max_score;

	public T getSource() {
		return _source;
	}
}
