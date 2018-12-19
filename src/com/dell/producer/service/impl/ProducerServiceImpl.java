package com.dell.producer.service.impl;

import java.util.List;

import com.dell.producer.Producer;
import com.dell.producer.service.ProducerService;

public class ProducerServiceImpl implements ProducerService {

	private List<List<Integer>> unSortedLists;
	
	public ProducerServiceImpl(List<List<Integer>> unSortedLists) {
		this.unSortedLists = unSortedLists;
	}
	
	@Override
	public List<List<Integer>> getUnSortedLists() {
		return unSortedLists;
	}

	@Override
	public void createProducers(int numberOfProducers ) {
		for (int i = 0; i < numberOfProducers; i++) {
			new Thread(new Producer(this, ProducerService.NO_OF_LIST_TO_BE_PRODUCED_BY_EACH_PRODUCER), "Producer " + (i + 1)).start();
		}
	}
}
