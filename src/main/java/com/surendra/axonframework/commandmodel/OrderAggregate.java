package com.surendra.axonframework.commandmodel;

import com.surendra.axonframework.coreapi.commands.ConfirmOrderCommand;
import com.surendra.axonframework.coreapi.commands.PlaceOrderCommand;
import com.surendra.axonframework.coreapi.commands.ShipOrderCommand;
import com.surendra.axonframework.coreapi.events.OrderConfirmedEvent;
import com.surendra.axonframework.coreapi.events.OrderPlacedEvent;
import com.surendra.axonframework.coreapi.events.OrderShippedEvent;
import com.surendra.axonframework.coreapi.exceptions.UnconfirmedOrderException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/***
 * As our domain focuses on dealing with Orders, we'll create an OrderAggregate as the center of our Command Model.
 *
 * The Aggregate annotation is an Axon Spring specific annotation marking this class as an aggregate
 */
@Aggregate
public class OrderAggregate {

    /***
     * As an aggregate will handle commands which are targeted for a specific aggregate instance, we need to specify the identifier with the AggregateIdentifier annotation.
     */

    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;

    /***
     * Our aggregate will commence its life cycle upon handling the PlaceOrderCommand in the OrderAggregate ‘command handling constructor'.
     * To tell the framework that the given function is able to handle commands, we'll add the CommandHandler annotation.
     *
     * When handling the PlaceOrderCommand, it will notify the rest of the application that an order was placed by publishing the OrderPlacedEvent.
     * To publish an event from within an aggregate, we'll use AggregateLifecycle#apply(Object…).
     * @param command
     */
    @CommandHandler
    public OrderAggregate(PlaceOrderCommand command) {
        apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        apply(new OrderConfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) {
        if (!orderConfirmed) {
            throw new UnconfirmedOrderException();
        }
        apply(new OrderShippedEvent(orderId));
    }


    /***
     * From this point, we can actually start to incorporate Event Sourcing as the driving force to recreate an aggregate instance from its stream of events.
     *
     * We start this off with the ‘aggregate creation event', the OrderPlacedEvent, which is handled in an EventSourcingHandler annotated function to set the orderId and orderConfirmed state of the Order aggregate.
     *
     * Also note that to be able to source an aggregate based on its events, Axon requires a default constructor.
     * @param event
     */
    @EventSourcingHandler
    public void on(OrderPlacedEvent event) {
        this.orderId = event.getOrderId();
        orderConfirmed = false;
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        orderConfirmed = true;
    }


    protected OrderAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }
}
