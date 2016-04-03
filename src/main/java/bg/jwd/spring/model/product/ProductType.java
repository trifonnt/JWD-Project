package bg.jwd.spring.model.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
@Table(name = "ws_product_type")
public class ProductType implements Serializable
//, ProductType
{

	private static final long serialVersionUID = 1L;

	protected static final Logger logger = LoggerFactory.getLogger(ProductType.class);

	@Id
	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;


	public ProductType() {
		super();
	}
	public ProductType(String name) {
		super();
		this.name = name;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

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