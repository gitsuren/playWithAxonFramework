package com.surendra.axonframework.coreapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

/***
 * We'll set up our Order service with CQRS in mind. Therefore we'll emphasize the messages that flow through our application.
 *
 * First, we'll define the Commands, meaning the expressions of intent. The Order service is capable of handling three different types of actions:
 *
 * Placing a new order
 * Confirming an order
 * Shipping an order
 */


//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class PlaceOrderCommand {

    /***
     * The TargetAggregateIdentifier annotation tells Axon that the annotated field is an id of a given aggregate to which the command should be targeted.
     */
    @TargetAggregateIdentifier
    private final String orderId;

    private final String product;

    // constructor, getters, equals/hashCode and toString

    public PlaceOrderCommand(String orderId, String product) {
        this.orderId = orderId;
        this.product = product;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProduct() {
        return product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, product);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final PlaceOrderCommand other = (PlaceOrderCommand) obj;
        return Objects.equals(this.orderId, other.orderId)
                && Objects.equals(this.product, other.product);
    }

    @Override
    public String toString() {
        return "PlaceOrderCommand{" +
                "orderId='" + orderId + '\'' +
                ", product='" + product + '\'' +
                '}';
    }
}