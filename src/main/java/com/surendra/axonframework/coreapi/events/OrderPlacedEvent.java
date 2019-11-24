package com.surendra.axonframework.coreapi.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/***
 * Our aggregate will handle the commands, as it's in charge of deciding if an Order can be placed, confirmed, or shipped.
 *
 * It will notify the rest of the application of its decision by publishing an event. We'll have three types of events — OrderPlacedEvent, OrderConfirmedEvent, and OrderShippedEvent:
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class OrderPlacedEvent {
    private final String orderId;
    private final String product;

    // default constructor, getters, equals/hashCode and toString

    public OrderPlacedEvent(String orderId, String product) {
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
        final OrderPlacedEvent other = (OrderPlacedEvent) obj;
        return Objects.equals(this.orderId, other.orderId)
                && Objects.equals(this.product, other.product);
    }

    @Override
    public String toString() {
        return "OrderPlacedEvent{" +
                "orderId='" + orderId + '\'' +
                ", product='" + product + '\'' +
                '}';
    }

}
