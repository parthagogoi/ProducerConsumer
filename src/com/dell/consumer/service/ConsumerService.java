package com.dell.consumer.service;

import java.util.List;

/**
 * 
 * @author partha
 *
 */
public interface ConsumerService {

	/**
	 * This method will return the lists of sorted list
	 * 
	 * @return sorted {@link List} of lists
	 */
	List<List<Integer>> getSortedList();

	/**
	 * This method will sort an unsroted list
	 * 
	 * @param unsortedList
	 *            -- list to be sorted
	 * @return copy of sorted list 
	 */
	List<Integer> sortList(List<Integer> unsortedList);

	/**
	 * This method will add the sorted list to the overall list
	 * 
	 * @param sortedList
	 *            -- list to be sorted
	 */
	void addList(List<Integer> sortedList);

	/**
	 * Method to create and start consumers as per input
	 * 
	 * @param numberOfConsumers
	 *            -- no. of consumers to be started
	 * @param totalMessageToBeConsumed
	 *            -- total messages/lists to be cosumed
	 */
	void createConsumers(int numberOfConsumers, int totalMessageToBeConsumed);

	/**
	 * This method will return the lists of unsorted list
	 * 
	 * @return unsorted {@link List} of lists
	 */
	List<List<Integer>> getUnSortedList();

	/**
	 * This method will return offset value - the position till messages are consumed
	 * 
	 * @return int
	 */
	int getOffSet();

	/**
	 * This method is used increment the offset
	 * 
	 */
	void incrementOffSet();

}
