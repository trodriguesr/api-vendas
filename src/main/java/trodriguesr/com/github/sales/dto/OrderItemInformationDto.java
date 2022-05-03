package trodriguesr.com.github.sales.dto;

import java.math.BigDecimal;

public class OrderItemInformationDto {

	private String productDescription;
	private BigDecimal unitPrice;
	private Long quantity;

	public OrderItemInformationDto() {

	}

	public OrderItemInformationDto(String productDescription, BigDecimal unitPrice, Long quantity) {
		super();
		this.productDescription = productDescription;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
