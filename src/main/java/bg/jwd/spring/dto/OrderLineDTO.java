package bg.jwd.spring.dto;

import java.math.BigDecimal;

import bg.jwd.spring.model.order.OrderLine;
import bg.jwd.spring.model.security.User;

public class OrderLineDTO {

	private String orderId;
	private String orderLineId;
	private String orderLineNumber;
	private String productNumber;
	private String productName;
	private String description;
	private String productTypeName;
	private String qty;
	private String price;
	private String totalNetAmt;


	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderLineId() {
		return orderLineId;
	}
	public void setOrderLineId(String orderLineId) {
		this.orderLineId = orderLineId;
	}

	public String getOrderLineNumber() {
		return orderLineNumber;
	}
	public void setOrderLineNumber(String orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}

	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public BigDecimal getQtyBD() {
		if (qty == null) {
			return null;
		} else {
			return new BigDecimal( qty );
		}
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty.toPlainString();
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public BigDecimal getPriceBD() {
		if (price == null) {
			return null;
		} else {
			return new BigDecimal( price );
		}
	}
	public void setPrice(BigDecimal price) {
		this.price = price.toPlainString();
	}

	public String getTotalNetAmt() {
		return totalNetAmt;
	}
	public void setTotalNetAmt(String totalNetAmt) {
		this.totalNetAmt = totalNetAmt;
	}
	public BigDecimal getTotalNetAmtBD() {
		if (totalNetAmt == null) {
			return null;
		} else {
			return new BigDecimal( totalNetAmt );
		}
	}
	public void setTotalNetAmt(BigDecimal totalNetAmt) {
		this.totalNetAmt = totalNetAmt.toPlainString();
	}

	public static OrderLineDTO constructDTO(OrderLine orderLine) {
		OrderLineDTO result = new OrderLineDTO();
		if (orderLine != null) {
//			re
		}
		return result;
	}
}