package com.dell.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.dell.consumer.service.ConsumerService;
import com.dell.consumer.service.impl.ConsumerServiceImpl;
import com.dell.producer.service.ProducerService;
import com.dell.producer.service.impl.ProducerServiceImpl;

public class ApplicationTest {

	@Test
	public void applicationTest() throws InterruptedException {
		List<List<Integer>> unsortedList = new ArrayList<>();
		System.out.println("Main starts now...");
		ProducerService producersService = new ProducerServiceImpl(unsortedList);
		ConsumerService consumerService = new ConsumerServiceImpl(unsortedList);
		
		int noOfProducers = 5;
		producersService.createProducers(noOfProducers);
		int totalProducedMessages =  noOfProducers * ProducerService.NO_OF_LIST_TO_BE_PRODUCED_BY_EACH_PRODUCER;
		consumerService.createConsumers(5, totalProducedMessages);
		
		List<List<Integer>> sortedList = consumerService.getSortedList();
		while(sortedList.size() < totalProducedMessages) {
			Thread.sleep(1000);
			continue;
		}
		
		for(int i = 0; i < totalProducedMessages; i++) {
			System.out.println("Produced " + unsortedList.get(i));
			System.out.println("Consumed " + sortedList.get(i));
			List<Integer> expectedOutout = new ArrayList<>();
			expectedOutout.addAll(unsortedList.get(i));
			List<Integer> outputList = sortedList.get(i);
			Collections.sort(expectedOutout);
			Assert.assertEquals(outputList, expectedOutout);
		}
		System.out.println("Main ends...");
	}
}
