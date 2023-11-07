package dev.codewithfriends;

import jdk.jfr.Event;
import jdk.jfr.Description;
import jdk.jfr.Label;

public class HelloProfiling {

  @Label("Hello World")
  @Description("Helps programmer getting started")
  static class HelloWorldEvent extends Event {
      @Label("Message")
      String message;
  }

  public static void main(String... args) {
      HelloWorldEvent event = new HelloWorldEvent();
      event.message = "hello, world!";
      event.commit();
  }
}