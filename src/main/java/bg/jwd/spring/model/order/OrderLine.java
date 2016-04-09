package bg.jwd.spring.model.order;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.jwd.spring.model.product.Product;
import bg.jwd.spring.model.security.User;


@Entity
@Table(name = "ws_order_line")
public class OrderLine implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static final Logger logger = LoggerFactory.getLogger(OrderLine.class);

	@Id
	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private Order order;

	@Column(name = "line_no")
	private String number;

	@Column(name = "description")
	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "creator_id")
	private User createdBy;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "qty")
	private BigDecimal qtyOrdered;

	@Column(name = "price")
	private BigDecimal priceEntered;

	@Column(name = "total_net_amt")
	private BigDecimal totalNetAmt;


	public OrderLine() {
		//
	}
	public OrderLine(Order order) {
		this.order = order;
		order.addLine( this );
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getQtyOrdered() {
		return qtyOrdered;
	}
	public void setQtyOrdered(BigDecimal qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

	public BigDecimal getPriceEntered() {
		return priceEntered;
	}
	public void setPriceEntered(BigDecimal priceEntered) {
		this.priceEntered = priceEntered;
	}

	public BigDecimal getTotalNetAmt() {
		return totalNetAmt;
	}
	public void setTotalNetAmt(BigDecimal totalNetAmt) {
		this.totalNetAmt = totalNetAmt;
	}
	@Override
	public String toString() {
		return "OrderLine ["
				+ "id=" + id
				+ ", order=" + order
				+ ", number=" + number
				+ ", description=" + description
				+ ", createdBy=" + createdBy
				+ ", product=" + product
				+ ", qtyOrdered=" + qtyOrdered
				+ ", priceEntered=" + priceEntered
				+ ", totalNetAmt=" + totalNetAmt
				+ "]";
	}
}