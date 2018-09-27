package com.kumar.actors;

public class SingleThread {

	public static void main(String[] args) throws InterruptedException {
		
		for (int index = 0; index < 10; index++) {
			for (int i=0; i<50;i++) {
				String text="Task"+ index +" counter-->"+i;
				System.out.println(text);
				Thread.sleep(1000);
			}
		}
		
	}
}
