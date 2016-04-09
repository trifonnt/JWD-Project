package bg.jwd.spring.model.common;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import bg.jwd.spring.model.security.User;


@Entity
@Table(name = "ws_location")
public class Location 
	implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "country")
	private String country;

	@Column(name = "state")
	private String state;

	@Column(name = "city")
	private String city;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "description")
	private String description;

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "creator_id")
	private User createdBy;


	public Location() {
		
	}
	public Location(String country, String state, String city, String address1, String address2, User createdBy) {
		if (country == null || country.isEmpty()) {
			throw new IllegalArgumentException("Country MUST not be null!");
		}
		if (state == null || state.isEmpty()) {
			throw new IllegalArgumentException("State MUST not be null!");
		}
		if (city == null || city.isEmpty()) {
			throw new IllegalArgumentException("City MUST not be null!");
		}
		if (address1 == null || address1.isEmpty()) {
			throw new IllegalArgumentException("Address1 MUST not be null!");
		}

		this.country = country;
		this.state = state;
		this.city = city;
		this.address1 = address1;
		this.address2 = address2;
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

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
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
	@Override
	public String toString() {
		return "Location ["
			+ "id=" + id
			+ ", country=" + country
			+ ", state=" + state
			+ ", city=" + city
			+ ", address1=" + address1
			+ ", address2=" + address2
			+ ", description=" + description
			+ ", createdBy=" + createdBy
		+ "]";
	}
}