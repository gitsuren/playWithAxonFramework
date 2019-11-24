package com.surendra.axonframework.coreapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ShipOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;

    // constructor, getters, equals/hashCode and toString

    public ShipOrderCommand(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ShipOrderCommand other = (ShipOrderCommand) obj;
        return Objects.equals(this.orderId, other.orderId);
    }

    @Override
    public String toString() {
        return "ShipOrderCommand{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}