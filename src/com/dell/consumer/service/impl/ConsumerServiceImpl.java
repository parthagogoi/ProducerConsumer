package com.dell.consumer.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.dell.consumer.Consumer;
import com.dell.consumer.service.ConsumerService;

/**
 * 
 * @author partha
 *
 */
public class ConsumerServiceImpl implements ConsumerService {

	private List<List<Integer>> unSortedLists;
	private List<List<Integer>> sortedLists = new LinkedList<>();
	private volatile int offset = 0;

	public ConsumerServiceImpl(List<List<Integer>> unSortedLists) {
		this.unSortedLists = unSortedLists;
	}

	@Override
	public List<List<Integer>> getSortedList() {
		return this.sortedLists;
	}

	@Override
	public List<List<Integer>> getUnSortedList() {
		return this.unSortedLists;
	}

	@Override
	public int getOffSet() {
		return offset;
	}

	@Override
	public void incrementOffSet() {
		this.offset = offset + 1;
	}

	@Override
	public void createConsumers(int numberOfConsumers, int totalMessageToBeConsumed) {
		for (int i = 0; i < numberOfConsumers; i++) {
			new Thread(new Consumer(this, totalMessageToBeConsumed), "Consumer " + (i + 1)).start();
		}
	}

	@Override
	public List<Integer> sortList(List<Integer> unsortedList) {
		List<Integer> copyOfList = new ArrayList<>(unsortedList.size());
		copyOfList.addAll(unsortedList);
		// As per the assignment, I'm using bubble sort here, but its better to
		// use merge sort or quick sort over bubble sort because of its high
		// time complexity
		bubbleSort(copyOfList);
		// Collections.sort(copyOfList);
		return copyOfList;
	}

	@Override
	public void addList(List<Integer> sortedList) {
		sortedLists.add(sortedList);
	}

	/**
	 * Bubble sort logic for sorting
	 * 
	 * @param unsortedList
	 *            -- list to be sort
	 */
	private void bubbleSort(List<Integer> unsortedList) {
		int size = unsortedList.size();
		for (int i = 0; i < size; i++) {
			for (int j = 1; j < (size - i); j++) {
				if (unsortedList.get(j - 1) > unsortedList.get(j)) {
					swap(unsortedList, j, j - 1);
				}
			}
		}
	}

	/**
	 * Logic for swapping values of two indexes from a list
	 * 
	 * @param list
	 *            -- list to be swapped
	 * @param index1
	 *            -- index to be swapped
	 * @param index2
	 *            -- index to be swapped
	 */
	private void swap(List<Integer> list, int index1, int index2) {
		int temp = 0;
		temp = list.get(index2);
		list.set(index2, list.get(index1));
		list.set(index1, temp);
	}

}
