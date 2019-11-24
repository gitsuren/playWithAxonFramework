package com.surendra.axonframework.coreapi.queries;


/***
 * The Query Model – Query Handlers
 * Next, to query this model, for example, to retrieve all the ordered products, we should first introduce a Query message to our core API:
 *
 * 1
 * public class FindAllOrderedProductsQuery { }
 * Second, we'll have to update the OrderedProductsEventHandler to be able to handle the FindAllOrderedProductsQuery:
 *
 * @QueryHandler
 * public List<OrderedProduct> handle(FindAllOrderedProductsQuery query) {
 *     return new ArrayList<>(orderedProducts.values());
 * }
 *
 * The QueryHandler annotated function will handle the FindAllOrderedProductsQuery and is set to return a List<OrderedProduct> regardless, similarly to any ‘find all' query.
 */
public class FindAllOrderedProductsQuery {
}
