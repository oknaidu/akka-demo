package com.kumar.actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MultiThreadActor {
	public static void main(String[] args) {
		ActorSystem _system = ActorSystem.create();

		for (int index = 0; index < 10; index++) {

			ActorRef ref = _system.actorOf(Props.create(ProcessActor.class),"Task"+index);
			for (int i = 0; i < 50; i++) {
				String text = "Task" + index + " counter-->" + i;
				ref.tell(text, ActorRef.noSender());

			}
		}

		
	}
}
