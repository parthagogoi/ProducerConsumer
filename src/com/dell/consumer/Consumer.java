package com.dell.consumer;

import java.util.List;

import com.dell.consumer.service.ConsumerService;

/**
 * 
 * @author partha
 *
 */
public class Consumer implements Runnable {
	private ConsumerService consumerService;
	private int totalMessageToBeConsumed;

	public Consumer(ConsumerService consumerService, int totalMessageToBeConsumed) {
		this.consumerService = consumerService;
		this.totalMessageToBeConsumed = totalMessageToBeConsumed;
	}

	@Override
	public void run() {
		while (consumerService.getSortedList().size() < totalMessageToBeConsumed) {
			try {
				consume();
			} catch (Exception e) {
				System.out.println("Exception from consumer " + e.getMessage());
				e.printStackTrace();
			}
		}
		
//		System.out.println("Exit .. " + Thread.currentThread().getName() );
	}

	/**
	 * Method to consume once there is any list available to be consumed in unsorted list
	 * 
	 * @throws InterruptedException
	 */
	private void consume() throws InterruptedException {
		if (consumerService.getUnSortedList().isEmpty()
				|| consumerService.getUnSortedList().size() <= consumerService.getOffSet()) {
			synchronized (consumerService.getUnSortedList()) {
//				System.out.println("Queue is empty, " + Thread.currentThread().getName() + " is waiting.");
				consumerService.getUnSortedList().wait();
			}
		}

		synchronized (consumerService.getUnSortedList()) {
			int offset = consumerService.getOffSet();
			// System.out.println("Current offset " + offset + ", " +
			// consumerService.getUnSortedList().size() + " : " +
			// Thread.currentThread().getName());
			if (!consumerService.getUnSortedList().isEmpty() && consumerService.getUnSortedList().size() > offset) {
				List<Integer> consumedList = consumerService.getUnSortedList().get(offset);
				List<Integer> sortedList = consumerService.sortList(consumedList);
				consumerService.getSortedList().add(sortedList);
//				System.out.println("Consumed: " + consumedList + ", by " + Thread.currentThread().getName());
				consumerService.incrementOffSet();
				consumerService.getUnSortedList().notifyAll();
			}
		}
	}

}
