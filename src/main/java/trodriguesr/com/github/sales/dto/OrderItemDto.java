package trodriguesr.com.github.sales.dto;

import java.util.Objects;

public class OrderItemDto {

	private Long product;
	private Long quantity;

	public OrderItemDto() {

	}

	public OrderItemDto(Long product, Long quantity) {

		this.product = product;
		this.quantity = quantity;
	}

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemDto other = (OrderItemDto) obj;
		return Objects.equals(product, other.product) && Objects.equals(quantity, other.quantity);
	}

}
