package bg.jwd.spring.model.product.impl;

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

import bg.jwd.spring.model.product.IProduct;
import bg.jwd.spring.model.product.IProductType;
import bg.jwd.spring.model.security.IUser;
import bg.jwd.spring.model.security.impl.UserImpl;


@Entity
@Table(name = "ws_product")
public class ProductImpl implements IProduct {

	private static final long serialVersionUID = 1L;

	protected static final Logger logger = LoggerFactory.getLogger(ProductImpl.class);

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

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ProductTypeImpl.class)
	@JoinColumn(name = "type_id")
	private IProductType type;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "qty_on_hand")
	private BigDecimal qtyOnHand;

	@ManyToOne(targetEntity = UserImpl.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "creator_id")
	private IUser createdBy;


	public ProductImpl() {
		
	}
	public ProductImpl(String productNumber, String name, IProductType type, IUser createdBy) {
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

	@Override
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	@Override
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

	@Override
	public IProductType getType() {
		return type;
	}
	public void setType(IProductType type) {
		this.type = type;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public BigDecimal getQtyOnHand() {
		return qtyOnHand;
	}
	public void setQtyOnHand(BigDecimal qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}

	@Override
	public IUser getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(IUser createdBy) {
		this.createdBy = createdBy;
	}
}