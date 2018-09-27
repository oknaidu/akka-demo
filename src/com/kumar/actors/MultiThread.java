package com.kumar.actors;

public class MultiThread {

	public static void main(String[] args) {
		
		for (int index = 0; index < 10; index++) {
			for (int i=0; i<50;i++) {
				final String text="Task"+ index +" counter-->"+i;
				Thread t=new Thread(new Runnable() {
					
					
					@Override
					public void run() {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(text);
					}
				});
				t.start();
			}
		}
		
	}
}
