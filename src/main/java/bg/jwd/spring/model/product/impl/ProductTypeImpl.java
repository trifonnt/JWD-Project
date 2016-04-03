package bg.jwd.spring.model.product.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.jwd.spring.model.product.IProductType;


@Entity
@Table(name = "ws_product_type")
public class ProductTypeImpl implements IProductType {

	private static final long serialVersionUID = 1L;

	protected static final Logger logger = LoggerFactory.getLogger(ProductTypeImpl.class);

	@Id
	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;


	public ProductTypeImpl() {
		super();
	}
	public ProductTypeImpl(String name) {
		super();
		this.name = name;
	}

	@Override
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductTypeImpl["
				+ "id=" + id
				+ ", name=" + name
				+ "]";
	}
}