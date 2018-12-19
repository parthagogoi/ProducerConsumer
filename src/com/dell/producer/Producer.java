package com.dell.producer;

import java.util.ArrayList;
import java.util.List;

import com.dell.producer.service.ProducerService;

/**
 * 
 * @author partha
 *
 */
public class Producer implements Runnable {
	private ProducerService producerService;
	private int noOfListToBeProduced;
	
	public Producer(ProducerService producerService, int  noOfListToBeProduced) {
		this.producerService = producerService;
		this.noOfListToBeProduced = noOfListToBeProduced;
	}

	@Override
	public void run() {
		for (int i = 0; i < noOfListToBeProduced; i++) {
			try {
				produceRandomNumber(i);
//				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Exception from producer");
			}
		}
	}

	/**
	 * Method to produce list 10 random numbers
	 * 
	 * @param iteration
	 * 			-- occurrence of this method
	 * @throws InterruptedException
	 */
	private void produceRandomNumber(int iteration) throws InterruptedException {
		List<Integer> randomNumbers = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			int value = (int) (Math.random() * 1000);
			randomNumbers.add(value);
		}
//		System.out.println("Produced random numbers " + randomNumbers + ", by " + Thread.currentThread().getName()
//				+ ", iteration " + (iteration + 1));
		synchronized (producerService.getUnSortedLists()) {
			producerService.getUnSortedLists().add(randomNumbers);
			producerService.getUnSortedLists().notifyAll();
		}
	}
}
