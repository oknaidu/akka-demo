package com.kumar.actors;

import com.kumar.actors.bo.Chat;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Identify;
import akka.actor.Props;

public class ActorTest {

	public static void main(String[] args) {
		ActorSystem _system=ActorSystem.create();
		ActorRef ref=_system.actorOf(Props.create(ReceiveActor.class));
		ref.tell(new Identify(10), null);
		ref.tell("Test", null);
		ref.tell(new Chat("Hello World!"), null);
	}
}
