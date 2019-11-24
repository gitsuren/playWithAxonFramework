# playWithAxonFramework
Through this project, I am learning AXON Framework

Referenced links:
```urls
https://www.baeldung.com/axon-cqrs-event-sourcing
https://github.com/eugenp/tutorials/tree/master/axon
```

#1. Overview
In this article, we'll be looking at Axon and how it helps us implement applications with CQRS (Command Query Responsibility Segregation) and Event Sourcing in mind.

During this guide both Axon Framework and Axon Server will be utilized. The former will contain our implementation and the latter will be our dedicated Event Store and Message Routing solution.

The sample application we'll be building focuses on an Order domain. For this, we'll be leveraging the CQRS and Event Sourcing building blocks Axon provides us.

Note that a lot of the shared concepts come right out of DDD, which is beyond the scope of this current article.


Other extra examples are :
```urls
https://github.com/abuijze/bootiful-axon
https://spring.io/blog/2016/10/25/webinar-bootiful-cqrs-with-axon-nov-16

https://github.com/AxonIQ/giftcard-demo
```
Axon Server
We will use Axon Server to be our Event Store and our dedicated command, event and query routing solution.

As an Event Store it gives us the ideal characteristics required when storing events. This article provides background why this is desirable.

As a Message Routing solution it gives us the option to connect several instances together without focusing on configuring things like a RabbitMQ or a Kafka topic to share and dispatch messages.

Axon Server can be downloaded here (https://download.axoniq.io/axonserver/AxonServer.zip). As it is a simple JAR file, the following operation suffices to start it up:

1
java -jar axonserver.jar
This will start a single Axon Server instance which is accessible through localhost:8024. The endpoint provides an overview of the connected applications and the messages they can handle, as well as a querying mechanism towards the Event Store contained within Axon Server.

The default configuration of Axon Server together with the axon-spring-boot-starter dependency will ensure our Order service will automatically connect to it.