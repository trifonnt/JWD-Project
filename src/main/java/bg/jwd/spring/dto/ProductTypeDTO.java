package bg.jwd.spring.dto;

import bg.jwd.spring.model.product.impl.ProductTypeImpl;

public class ProductTypeDTO {

	private long id;
	private String name;
	private String description;
	private String creatorName;


	public ProductTypeDTO() {
		
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

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public static ProductTypeDTO constructDto(ProductTypeImpl productType) {
		ProductTypeDTO result = new ProductTypeDTO();
		if (productType != null) {
			result.setId( productType.getId() );
			result.setName( productType.getName() );
//			result.setDescription( productType.getDescription() );
		}
		return result;
	}

	@Override
	public String toString() {
		return "ProductTypeDTO ["
				+ "id=" + id
				+ ", name=" + name
				+ ", description=" + description
				+ ", creatorName=" + creatorName
				+ "]";
	}
}