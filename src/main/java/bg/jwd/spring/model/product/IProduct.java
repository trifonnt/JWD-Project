package bg.jwd.spring.model.product;

import java.io.Serializable;
import java.math.BigDecimal;

import bg.jwd.spring.model.security.IUser;


public interface IProduct extends Serializable {

	long getId();

	String getNumber();

	String getName();

	String getDescription();

	IProductType getType();

	BigDecimal getPrice();

	BigDecimal getQtyOnHand();

	IUser getCreatedBy();
}