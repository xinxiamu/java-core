//package com.ymu.akkaactor.helloword;
//
//import akka.actor.UntypedAbstractActor;
//import akka.actor.typed.javadsl.Behaviors;
//
//public class HelloWorld extends AbstractBehavior<HelloWorld.SayHello> {
//    public static Behavior<SayHello> create() {
//        return Behaviors.setup(HelloWorldMain::new);
//    }
//
//    private final ActorRef<HelloWorld.Greet> greeter;
//
//    private HelloWorld(ActorContext<SayHello> context) {
//        super(context);
//        greeter = context.spawn(HelloWorld.create(), "greeter");
//    }
//}
