package com.kumar.actors;

import akka.actor.ActorSystem;
import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;

public class Main {

	public static void main(String[] args) {
		/*final ActorSystem<HelloWorldMain.Start> system =
				  ActorSystem.create(HelloWorldMain.main, "hello");

				system.tell(new HelloWorldMain.Start("World"));
				system.tell(new HelloWorldMain.Start("Akka"));*/
		
	}


public abstract static class HelloWorldMain {
	  private HelloWorldMain() {
	  }

	  public static class Start {
	    public final String name;

	    public Start(String name) {
	      this.name = name;
	    }
	  }

	  public static final Behavior<Start> main =
	    Behaviors.setup( context -> {
	      final ActorRef<HelloWorld.Greet> greeter =
	          context.spawn(HelloWorld.greeter, "greeter");

	      return Behaviors.receiveMessage(msg -> {
	        ActorRef<HelloWorld.Greeted> replyTo =
	            context.spawn(HelloWorldBot.bot(0, 3), msg.name);
	        greeter.tell(new HelloWorld.Greet(msg.name, replyTo));
	        return Behaviors.same();
	      });
	    });
	}
public abstract static class HelloWorld {
	  //no instances of this class, it's only a name space for messages
	  // and static methods
	  private HelloWorld() {
	  }

	  public static final class Greet{
	    public final String whom;
	    public final ActorRef<Greeted> replyTo;

	    public Greet(String whom, ActorRef<Greeted> replyTo) {
	      this.whom = whom;
	      this.replyTo = replyTo;
	    }
	  }

	  public static final class Greeted {
	    public final String whom;
	    public final ActorRef<Greet> from;

	    public Greeted(String whom, ActorRef<Greet> from) {
	      this.whom = whom;
	      this.from = from;
	    }
	  }

	  public static final Behavior<Greet> greeter = Behaviors.receive((ctx, msg) -> {
	    ctx.getLog().info("Hello {}!", msg.whom);
	    msg.replyTo.tell(new Greeted(msg.whom, ctx.getSelf()));
	    return Behaviors.same();
	  });
	}
public abstract static class HelloWorldBot {
	  private HelloWorldBot() {
	  }

	  public static final Behavior<HelloWorld.Greeted> bot(int greetingCounter, int max) {
	    return Behaviors.receive((ctx, msg) -> {
	      int n = greetingCounter + 1;
	      ctx.getLog().info("Greeting {} for {}", n, msg.whom);
	      if (n == max) {
	        return Behaviors.stopped();
	      } else {
	        msg.from.tell(new HelloWorld.Greet(msg.whom, ctx.getSelf()));
	        return bot(n, max);
	      }
	    });
	  }
	}

}