package com.surendra.axonframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AxonFrameworkOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxonFrameworkOrderApplication.class, args);
	}


	/***
	 *
	 *
	 * Putting Everything Together
	 * We've fleshed out our core API with commands, events, and queries, and set up our Command and Query model by having an OrderAggregate and OrderedProducts model.
	 *
	 * Next is to tie up the loose ends of our infrastructure. As we're using the axon-spring-boot-starter, this sets a lot of the required configuration automatically.
	 *
	 * First, as we want to leverage Event Sourcing for our Aggregate, we'll need an EventStore. Axon Server which we have started up in step three will fill this hole.
	 *
	 * Secondly, we need a mechanism to store our OrderedProduct query model. For this example, we can add h2 as an in-memory database and spring-boot-starter-data-jpa for ease of use:
	 */

}
