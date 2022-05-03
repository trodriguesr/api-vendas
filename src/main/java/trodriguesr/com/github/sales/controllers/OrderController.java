package trodriguesr.com.github.sales.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import trodriguesr.com.github.sales.dto.OrderDto;
import trodriguesr.com.github.sales.dto.OrderInformationDto;
import trodriguesr.com.github.sales.dto.OrderItemInformationDto;
import trodriguesr.com.github.sales.dto.OrderStatusUpdateDto;
import trodriguesr.com.github.sales.enums.OrderStatus;
import trodriguesr.com.github.sales.models.entities.Order;
import trodriguesr.com.github.sales.models.entities.OrderItem;
import trodriguesr.com.github.sales.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService service;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Long save(@RequestBody @Valid OrderDto dto) {

		Order order = service.save(dto);
		return order.getId();
	}

	@GetMapping("{id}")
	public OrderInformationDto getById(@PathVariable Long id) {
		return service.getCompleteOrder(id).map(order -> converting(order))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found."));
	}

	@PatchMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Long id, @RequestBody OrderStatusUpdateDto dto) {
		String newStatus = dto.getNewStatus();
		service.updateStatus(id, OrderStatus.valueOf(newStatus));
	}

	private OrderInformationDto converting(Order order) {

		OrderInformationDto orderInfo = new OrderInformationDto();

		orderInfo.setCode(order.getId());
		orderInfo.setOrderDate(order.getDateOrder().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		orderInfo.setCpf(order.getClient().getCpf());
		orderInfo.setClientName(order.getClient().getName());
		orderInfo.setTotal(order.getTotal());
		orderInfo.setStatus(order.getStatus().name());
		orderInfo.setItems(converting(order.getItems()));

		return orderInfo;

	}

	private List<OrderItemInformationDto> converting(List<OrderItem> items) {
		if (CollectionUtils.isEmpty(items)) {
			return Collections.emptyList();
		}

		OrderItemInformationDto orderItemInfo = new OrderItemInformationDto();

		return items.stream().map(item -> {

			orderItemInfo.setProductDescription(item.getProduct().getDescription());
			orderItemInfo.setUnitPrice(item.getProduct().getPrice());
			orderItemInfo.setQuantity(item.getQuantity());

			return orderItemInfo;

		}).collect(Collectors.toList());
	}

}
