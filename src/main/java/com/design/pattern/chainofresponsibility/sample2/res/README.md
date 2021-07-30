---
layout: pattern
title: Chain of responsibility
folder: chain-of-responsibility
permalink: /patterns/chain-of-responsibility/
categories: Behavioral
language: en
tags:
 - Gang of Four
---

## Intent

Avoid coupling the sender of a request to its receiver by giving more than one object a chance to 
handle the request. Chain the receiving objects and pass the request along the chain until an object 
handles it.

## Explanation

Real-world example

> The Orc King gives loud orders to his army. The closest one to react is the commander, then 
> an officer, and then a soldier. The commander, officer, and soldier form a chain of responsibility.

In plain words

> It helps to build a chain of objects. A request enters from one end and keeps going from an object 
> to another until it finds a suitable handler.

Wikipedia says

> In object-oriented design, the chain-of-responsibility pattern is a design pattern consisting of 
> a source of command objects and a series of processing objects. Each processing object contains 
> logic that defines the types of command objects that it can handle; the rest are passed to the 
> next processing object in the chain.

**Programmatic Example**

Translating our example with the orcs from above. First, we have the `Request` class:

```java
public class Request {

  private final RequestType requestType;
  private final String requestDescription;
  private boolean handled;

  public Request(final RequestType requestType, final String requestDescription) {
    this.requestType = Objects.requireNonNull(requestType);
    this.requestDescription = Objects.requireNonNull(requestDescription);
  }

  public String getRequestDescription() { return requestDescription; }

  public RequestType getRequestType() { return requestType; }

  public void markHandled() { this.handled = true; }

  public boolean isHandled() { return this.handled; }

  @Override
  public String toString() { return getRequestDescription(); }
}

public enum RequestType {
  DEFEND_CASTLE, TORTURE_PRISONER, COLLECT_TAX
}
```

Next, we show the request handler hierarchy.

```java
@Slf4j
public abstract class RequestHandler {
  private final RequestHandler next;

  public RequestHandler(RequestHandler next) {
    this.next = next;
  }

  public void handleRequest(Request req) {
    if (next != null) {
      next.handleRequest(req);
    }
  }

  protected void printHandling(Request req) {
    LOGGER.info("{} handling request \"{}\"", this, req);
  }

  @Override
  public abstract String toString();
}

public class OrcCommander extends RequestHandler {
  public OrcCommander(RequestHandler handler) {
    super(handler);
  }

  @Override
  public void handleRequest(Request req) {
    if (req.getRequestType().equals(RequestType.DEFEND_CASTLE)) {
      printHandling(req);
      req.markHandled();
    } else {
      super.handleRequest(req);
    }
  }

  @Override
  public String toString() {
    return "Orc commander";
  }
}

// OrcOfficer and OrcSoldier are defined similarly as OrcCommander

```

The Orc King gives the orders and forms the chain.

```java
public class OrcKing {
  RequestHandler chain;

  public OrcKing() {
    buildChain();
  }

  private void buildChain() {
    chain = new OrcCommander(new OrcOfficer(new OrcSoldier(null)));
  }

  public void makeRequest(Request req) {
    chain.handleRequest(req);
  }
}
```

The chain of responsibility in action.

```java
var king = new OrcKing();
king.makeRequest(new Request(RequestType.DEFEND_CASTLE, "defend castle"));
king.makeRequest(new Request(RequestType.TORTURE_PRISONER, "torture prisoner"));
king.makeRequest(new Request(RequestType.COLLECT_TAX, "collect tax"));
```

The console output.

```
Orc commander handling request "defend castle"
Orc officer handling request "torture prisoner"
Orc soldier handling request "collect tax"
```

## Class diagram

![alt text](./etc/chain-of-responsibility.urm.png "Chain of Responsibility class diagram")

## Applicability

Use Chain of Responsibility when

* More than one object may handle a request, and the handler isn't known a priori. The handler should be ascertained automatically.
* You want to issue a request to one of several objects without specifying the receiver explicitly.
* The set of objects that can handle a request should be specified dynamically.

## Known uses

* [java.util.logging.Logger#log()](http://docs.oracle.com/javase/8/docs/api/java/util/logging/Logger.html#log%28java.util.logging.Level,%20java.lang.String%29)
* [Apache Commons Chain](https://commons.apache.org/proper/commons-chain/index.html)
* [javax.servlet.Filter#doFilter()](http://docs.oracle.com/javaee/7/api/javax/servlet/Filter.html#doFilter-javax.servlet.ServletRequest-javax.servlet.ServletResponse-javax.servlet.FilterChain-)

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
