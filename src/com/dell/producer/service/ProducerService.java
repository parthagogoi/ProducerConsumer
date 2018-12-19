package com.dell.producer.service;

import java.util.List;

/**
 * 
 * @author partha
 *
 */
public interface ProducerService {
	
	int NO_OF_LIST_TO_BE_PRODUCED_BY_EACH_PRODUCER = 100;

	/**
	 * Method to create and start producers as per input
	 * 
	 * @param numberOfProducers
	 * 			-- no. of producers to be started
	 */
	void createProducers(int numberOfProducers);

	/**
	 * This method will return the lists of unsorted list
	 * 
	 * @return unsorted {@link List} of lists
	 */
	List<List<Integer>> getUnSortedLists();

	

}
