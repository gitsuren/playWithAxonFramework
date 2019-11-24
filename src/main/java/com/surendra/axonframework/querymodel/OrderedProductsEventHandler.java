package com.surendra.axonframework.querymodel;

import com.surendra.axonframework.coreapi.events.OrderConfirmedEvent;
import com.surendra.axonframework.coreapi.events.OrderPlacedEvent;
import com.surendra.axonframework.coreapi.events.OrderShippedEvent;
import com.surendra.axonframework.coreapi.queries.FindAllOrderedProductsQuery;
import com.surendra.axonframework.coreapi.queries.OrderedProduct;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * We'll update this model based on the events propagating through our system. A Spring Service bean to update our model will do the trick:
 */
@Service
@ProcessingGroup("ordered-products")
public class OrderedProductsEventHandler {

    private final Map<String, OrderedProduct> orderedProducts = new HashMap<>();

    /***
     *
     * As we've used the axon-spring-boot-starter dependency to initiate our Axon application, the framework will automatically scan all the beans for existing message-handling functions.
     *
     * As the OrderedProductsEventHandler has EventHandler annotated functions to store an OrderedProduct and update it,
     * this bean will be registered by the framework as a class that should receive events without requiring any configuration on our part.
     * @param event
     */
    @EventHandler
    public void on(OrderPlacedEvent event) {
        String orderId = event.getOrderId();
        orderedProducts.put(orderId, new OrderedProduct(orderId, event.getProduct()));
    }

    @EventHandler
    public void on(OrderConfirmedEvent event) {
        orderedProducts.computeIfPresent(event.getOrderId(), (orderId, orderedProduct) -> {
            orderedProduct.setOrderConfirmed();
            return orderedProduct;
        });
    }

    @EventHandler
    public void on(OrderShippedEvent event) {
        orderedProducts.computeIfPresent(event.getOrderId(), (orderId, orderedProduct) -> {
            orderedProduct.setOrderShipped();
            return orderedProduct;
        });
    }

    @QueryHandler
    public List<OrderedProduct> handle(FindAllOrderedProductsQuery query) {
        return new ArrayList<>(orderedProducts.values());
    }

}