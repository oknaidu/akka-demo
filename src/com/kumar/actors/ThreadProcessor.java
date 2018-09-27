package com.kumar.actors;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadProcessor extends Thread{

	private Queue<String> messageQueue=new LinkedBlockingQueue<>();

	boolean bRunning=true;
	
	public void addMessage(String text)
	{
		messageQueue.add(text);
	}
	
	@Override
	public void run() {
		
		while(bRunning)
		{
			if(messageQueue.size()>0)
			{
				String text=messageQueue.poll();
				System.out.println(Thread.currentThread().getName()+" :"+text);
			}
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
