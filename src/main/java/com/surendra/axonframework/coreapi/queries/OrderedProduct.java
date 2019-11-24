package com.surendra.axonframework.coreapi.queries;

import java.util.Objects;

/***
 *
 * The Query Model – Event Handlers
 * So far, we've established our core API with the commands and events, and we have the Command model of our CQRS Order service, the Order aggregate, in place.
 *
 * Next, we can start thinking of one of the Query Models our application should service.
 *
 * One of these models is the OrderedProducts:
 *
 */
public class OrderedProduct {
    private final String orderId;
    private final String product;
    private OrderStatus orderStatus;

    public OrderedProduct(String orderId, String product) {
        this.orderId = orderId;
        this.product = product;
        orderStatus = OrderStatus.PLACED;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProduct() {
        return product;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderConfirmed() {
        this.orderStatus = OrderStatus.CONFIRMED;
    }

    public void setOrderShipped() {
        this.orderStatus = OrderStatus.SHIPPED;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, product, orderStatus);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final OrderedProduct other = (OrderedProduct) obj;
        return Objects.equals(this.orderId, other.orderId)
                && Objects.equals(this.product, other.product)
                && Objects.equals(this.orderStatus, other.orderStatus);
    }

    @Override
    public String toString() {
        return "OrderedProduct{" +
                "orderId='" + orderId + '\'' +
                ", product='" + product + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
