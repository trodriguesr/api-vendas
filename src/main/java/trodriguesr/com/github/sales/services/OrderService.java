package trodriguesr.com.github.sales.services;

import java.util.Optional;

import trodriguesr.com.github.sales.dto.OrderDto;
import trodriguesr.com.github.sales.enums.OrderStatus;
import trodriguesr.com.github.sales.models.entities.Order;

public interface OrderService {
	
	public Order save(OrderDto dto);
	
	public Optional<Order> getCompleteOrder(Long id);
	
	public void updateStatus(Long id, OrderStatus orderStatus);
	
}
