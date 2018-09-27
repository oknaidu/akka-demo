package com.kumar.actors;

import akka.actor.AbstractActor;

public class ProcessActor extends AbstractActor{

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(String.class, id->{
			System.out.println(this.getSelf().path().toString()+":"+id);
			Thread.sleep(1000);
		}).build();
		
	}

}
