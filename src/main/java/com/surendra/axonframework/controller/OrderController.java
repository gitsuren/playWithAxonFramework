package com.surendra.axonframework.controller;

import com.surendra.axonframework.coreapi.commands.ConfirmOrderCommand;
import com.surendra.axonframework.coreapi.commands.PlaceOrderCommand;
import com.surendra.axonframework.coreapi.commands.ShipOrderCommand;
import com.surendra.axonframework.coreapi.queries.FindAllOrderedProductsQuery;
import com.surendra.axonframework.coreapi.queries.OrderedProduct;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    /***
     * The CommandGateway is used as the mechanism to send our command messages, and the QueryGateway, in turn, to send query messages.
     * The gateways provide a simpler, more straightforward API, compared to the CommandBus and QueryBus that they connect with.
     * @param commandGateway
     * @param queryGateway
     */

    public OrderController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    /***
     * POST-ing to endpoint /ship-order will instantiate an OrderAggregate that'll publish events, which, in turn, will save/update our OrderedProducts.
     *
     * POINT TO BE NOTED HERE IS THAT THE RETURN TYPE IS VOID
     */
    @PostMapping("/ship-order")
    public void shipOrder() {
        String orderId = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(orderId, "Deluxe Chair"));
        commandGateway.send(new ConfirmOrderCommand(orderId));
        commandGateway.send(new ShipOrderCommand(orderId));
    }

    @PostMapping("/ship-unconfirmed-order")
    public void shipUnconfirmedOrder() {
        String orderId = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(orderId, "Deluxe Chair"));
        // This throws an exception, as an Order cannot be shipped if it has not been confirmed yet.
        commandGateway.send(new ShipOrderCommand(orderId));
    }

    /***
     * In the GET endpoint, we leverage the QueryGateway to dispatch a point-to-point query. In doing so, we create a default FindAllOrderedProductsQuery,
     * but we also need to specify the expected return type.
     *
     * As we expect multiple OrderedProduct instances to be returned, we leverage the static ResponseTypes#multipleInstancesOf(Class) function.
     * With this, we have provided a basic entrance into the Query side of our Order service.
     *
     * GET-ing from the /all-orders endpoint will publish a query message that'll be handled by the OrderedProductsEventHandler, which will return all the existing OrderedProducts.
     * @return
     */

    @GetMapping("/all-orders")
    public List<OrderedProduct> findAllOrderedProducts() {
        return queryGateway.query(new FindAllOrderedProductsQuery(), ResponseTypes.multipleInstancesOf(OrderedProduct.class))
                .join();
    }
}
