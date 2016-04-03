package bg.jwd.spring.model.product;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.jwd.spring.model.security.User;


@Entity
@Table(name = "ws_product")
public class Product implements Serializable
//, IProduct
{

	private static final long serialVersionUID = 1L;

	protected static final Logger logger = LoggerFactory.getLogger(Product.class);

	@Id
	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "prd_number")
	private String number;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ProductType.class)
	@JoinColumn(name = "type_id")
	private ProductType type;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "qty_on_hand")
	private BigDecimal qtyOnHand;

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "creator_id")
	private User createdBy;


	public Product() {
		
	}
	public Product(String productNumber, String name, ProductType type, User createdBy) {
		super();
		if (productNumber == null || productNumber.isEmpty()) {
			throw new IllegalArgumentException("ProductNumber MUST not be null!");
		}
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name MUST not be null!");
		}

		this.number = productNumber;
		this.name = name;
		this.price = BigDecimal.ZERO;
		this.qtyOnHand = BigDecimal.ZERO;
		if (createdBy == null) {
//			this.createdBy = UserUtils.getCurrentUser(); // TODO
		} else {
			this.createdBy = createdBy;
		}
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQtyOnHand() {
		return qtyOnHand;
	}
	public void setQtyOnHand(BigDecimal qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}

	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
}