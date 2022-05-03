package trodriguesr.com.github.sales.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderInformationDto {

	private Long code;
	private String cpf;
	private String clientName;
	private BigDecimal total;
	private String orderDate;
	private String status;
	private List<OrderItemInformationDto> items;

	public OrderInformationDto() {

	}

	public OrderInformationDto(Long code, String cpf, String clientName, BigDecimal total, String orderDate,
			String status, List<OrderItemInformationDto> items) {
		this.code = code;
		this.cpf = cpf;
		this.clientName = clientName;
		this.total = total;
		this.orderDate = orderDate;
		this.status = status;
		this.items = items;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItemInformationDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemInformationDto> items) {
		this.items = items;
	}

}
