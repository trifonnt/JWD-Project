package bg.jwd.spring.dto;

import java.math.BigDecimal;

import bg.jwd.spring.model.product.Product;

public class ProductDTO {

	private long id;
	private String productNumber;
	private String name;
	private String description;
	private long typeId;
	private String typeName;
	private String price;
	private BigDecimal priceBd;
	private String qtyOnHand;
	private BigDecimal qtyOnHandBd;
	private String creatorName;


	public ProductDTO() {
		
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String number) {
		this.productNumber = number;
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

	public long getTypeId() {
		return typeId;
	}
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
		if (this.price != null) {
			this.priceBd = new BigDecimal( price );
		} else {
			this.priceBd = null;
		}
	}
	public void setPriceBd(BigDecimal priceBd) {
		if (priceBd != null) {
			this.priceBd = priceBd;
			this.price = priceBd.toPlainString();
		}
	}
	public BigDecimal getPriceBd() {
		return priceBd;
	}

	public String getQtyOnHand() {
		return qtyOnHand;
	}
	public void setQtyOnHand(String qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
		if (this.qtyOnHand != null) {
			this.qtyOnHandBd = new BigDecimal( qtyOnHand );
		} else {
			this.qtyOnHandBd = null;
		}
	}
	public void setQtyOnHandBd(BigDecimal qtyOnHandBd) {
		if (qtyOnHandBd != null) {
			this.qtyOnHandBd = qtyOnHandBd;
			this.qtyOnHand = qtyOnHandBd.toPlainString();
		}
	}
	public BigDecimal getQtyOnHandBd() {
		return qtyOnHandBd;
	}

	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public static ProductDTO constructDto(Product product) {
		ProductDTO result = new ProductDTO();
		if (product != null) {
			result.setId( product.getId() );
			result.setProductNumber( product.getNumber() );
			result.setName( product.getName() );
			result.setDescription( product.getDescription() );
			result.setPriceBd( product.getPrice() );
			result.setQtyOnHandBd( product.getQtyOnHand() );
			if (product.getType() != null) {
				result.setTypeId( product.getType().getId() );
				result.setTypeName( product.getType().getName() );
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "ProductDTO ["
				+ "id=" + id
				+ ", productNumber=" + productNumber
				+ ", name=" + name
				+ ", description=" + description
				+ ", typeName=" + typeName
				+ ", price=" + price
				+ ", qtyOnHand=" + qtyOnHand
				+ ", creatorName=" + creatorName
				+ "]";
	}
}