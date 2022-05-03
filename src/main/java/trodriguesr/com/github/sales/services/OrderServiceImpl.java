package trodriguesr.com.github.sales.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import trodriguesr.com.github.sales.dto.OrderDto;
import trodriguesr.com.github.sales.dto.OrderItemDto;
import trodriguesr.com.github.sales.enums.OrderStatus;
import trodriguesr.com.github.sales.exception.BusinessRulesException;
import trodriguesr.com.github.sales.exception.OrderNotFoundException;
import trodriguesr.com.github.sales.models.entities.Client;
import trodriguesr.com.github.sales.models.entities.Order;
import trodriguesr.com.github.sales.models.entities.OrderItem;
import trodriguesr.com.github.sales.models.entities.Product;
import trodriguesr.com.github.sales.models.repositories.ClientRepository;
import trodriguesr.com.github.sales.models.repositories.OrderItemRepository;
import trodriguesr.com.github.sales.models.repositories.OrderRepository;
import trodriguesr.com.github.sales.models.repositories.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	@Transactional
	public Order save(OrderDto dto) {

		Long idClient = dto.getClient();
		Client client = clientRepository.findById(idClient)
				.orElseThrow(() -> new BusinessRulesException("Invalid client code."));

		Order order = new Order();
		order.setTotal(dto.getTotal());
		order.setDateOrder(LocalDate.now());
		order.setClient(client);
		order.setStatus(OrderStatus.COMPLETED);

		List<OrderItem> orderItems = ordering(order, dto.getItems());
		repository.save(order);
		orderItemRepository.saveAll(orderItems);
		order.setItems(orderItems);

		return order;

	}

	private List<OrderItem> ordering(Order order, List<OrderItemDto> items) {

		if (items.isEmpty()) {
			throw new BusinessRulesException("Unable to order without items.");
		}

		return items.stream().map(dto -> {
			Long idProduct = dto.getProduct();
			Product product = productRepository.findById(idProduct)
					.orElseThrow(() -> new BusinessRulesException("Invalid product code: " + idProduct));

			OrderItem orderItem = new OrderItem();
			orderItem.setQuantity(dto.getQuantity());
			orderItem.setOrder(order);
			orderItem.setProduct(product);
			return orderItem;

		}).collect(Collectors.toList());
	}

	@Override
	public Optional<Order> getCompleteOrder(Long id) {

		return repository.findByIdFetchItems(id);
	}

	@Override
	@Transactional
	public void updateStatus(Long id, OrderStatus orderStatus) {

		repository.findById(id).map(order -> {
			order.setStatus(orderStatus);
			return repository.save(order);
		}).orElseThrow(() -> new OrderNotFoundException());

	}

}
