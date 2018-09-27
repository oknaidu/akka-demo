package com.kumar.actors;

import com.kumar.actors.bo.Chat;

import akka.actor.AbstractActor;
import akka.actor.ActorIdentity;

public class ReceiveActor extends AbstractActor {

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(ActorIdentity.class, id->{
			System.out.println(id);
		})
				.match(Chat.class, this::chatReceived)
		.matchAny(m->{
			System.out.println(m);
		})
				.build();
	}

	
	private void chatReceived(Chat chat){
		System.out.println(chat.getMessage());
	}
	
}
