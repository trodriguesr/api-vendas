package trodriguesr.com.github.sales.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class OrderDto {

	@NotNull(message = "Informe o código do cliente.")
	private Long client;

	@NotNull(message = "Total de pedidos está vazio.")
	private BigDecimal total;

	private List<OrderItemDto> items;

	public OrderDto() {

	}

	public OrderDto(Long client, BigDecimal total, List<OrderItemDto> items) {

		this.client = client;
		this.total = total;
		this.items = items;
	}

	public Long getClient() {
		return client;
	}

	public void setClient(Long client) {
		this.client = client;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<OrderItemDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client, items, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDto other = (OrderDto) obj;
		return Objects.equals(client, other.client) && Objects.equals(items, other.items)
				&& Objects.equals(total, other.total);
	}

}
