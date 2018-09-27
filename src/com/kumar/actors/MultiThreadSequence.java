package com.kumar.actors;

public class MultiThreadSequence {

public static void main(String[] args) {
		
		for (int index = 0; index < 10; index++) {
			
			ThreadProcessor p=new ThreadProcessor();
			p.start();
			for (int i=0; i<50;i++) {
				String text="Task"+ index +" counter-->"+i;
				p.addMessage(text);
				
			}
		}
		
	}
	
}
