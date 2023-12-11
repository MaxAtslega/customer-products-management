package dev.atslega.cpmb.service;

import dev.atslega.cpmb.dto.OrderResponse;
import dev.atslega.cpmb.dto.ProductResponse;
import dev.atslega.cpmb.exception.ResourceNotFoundException;
import dev.atslega.cpmb.model.Order;
import dev.atslega.cpmb.repository.OrderRepository;
import dev.atslega.cpmb.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductMapper productMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.orderRepository = orderRepository;
    }

    public List<OrderResponse> getAllOrders(Long companyId, Integer size, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, size);

        List<Order> orders = orderRepository.findByCompanyId(companyId, page).stream().filter(c ->
                Objects.equals(c.getCompany().getId(), companyId)).toList();

        return orders.stream().map(this::mapOrdersToOrderResponse).toList();
    }

    public OrderResponse mapOrdersToOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setProducts(order.getProducts().stream().map(productMapper::toResponse).toArray(ProductResponse[]::new));
        orderResponse.setCustomer(order.getCustomer().getId());
        orderResponse.setId(order.getId());

        return orderResponse;
    }

    public OrderResponse getOrderById(Long id, Long companyId) {
        abortIfOrderDoesNotExist(id, companyId);
        return mapOrdersToOrderResponse(orderRepository.findById(id).orElseThrow());
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order, Long companyId) {
        abortIfOrderDoesNotExist(order.getId(), companyId);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id, Long companyId) {
        abortIfOrderDoesNotExist(id, companyId);
        orderRepository.deleteById(id);
    }

    private void abortIfOrderDoesNotExist(Long id, Long companyId) {
        orderRepository.findById(id).filter(c -> Objects.equals(c.getCompany().getId(), companyId)).orElseThrow(() -> new ResourceNotFoundException(Order.class.getSimpleName(), id));
    }
}
