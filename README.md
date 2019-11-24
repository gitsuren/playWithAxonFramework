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
