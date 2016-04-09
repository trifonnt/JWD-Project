package bg.jwd.spring.model.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.jwd.spring.model.common.Location;
import bg.jwd.spring.model.security.User;


@Entity
@Table(name = "ws_order")
public class Order implements Serializable
{

	private static final long serialVersionUID = 1L;

	protected static final Logger logger = LoggerFactory.getLogger(Order.class);

	@Id
	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "order_number")
	private String number;

	@Column(name = "description")
	private String description;

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "creator_id")
	private User createdBy;

	@Column(name = "completed")
	private boolean completed;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_partner_id")
	private User orderPartner;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_partner_location_id")
	private Location orderLocation;

	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private Collection<OrderLine> lines;

	public Order() {
		lines = new ArrayList<OrderLine>();
	}
	public Order(User orderPartner, Location orderLocation, User creator) {
		this(); // In order to initialize OrderLine!

		if (orderPartner == null) {
			throw new IllegalArgumentException("Order Partner MUST not be null!");
		}
//		if (orderLocation == null) {
//			throw new IllegalArgumentException("Order Partner Location MUST not be null!");
//		}
		this.orderPartner = orderPartner;
		this.orderLocation = orderLocation;
		this.createdBy = creator;
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

	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public User getOrderPartner() {
		return orderPartner;
	}
	public void setOrderPartner(User orderPartner) {
		this.orderPartner = orderPartner;
	}

	public Location getOrderLocation() {
		return orderLocation;
	}
	public void setOrderLocation(Location orderLocation) {
		this.orderLocation = orderLocation;
	}

	public Collection<OrderLine> getLines() {
		return lines;
	}
	public void setLines(Collection<OrderLine> lines) {
		this.lines = lines;
	}
	public void addLine(OrderLine line) {
		if (line == null) {
			throw new IllegalArgumentException("OrderLine MUST not be null!");
		} else {
			lines.add( line );
		}
	}

	@Override
	public String toString() {
		return "Order ["
			+   "id=" + id
			+ ", number=" + number
			+ ", description=" + description
			+ ", createdBy=" + createdBy
			+ ", completed=" + completed
			+ ", orderPartner=" + orderPartner
			+ ", orderLocation=" + orderLocation
		+ "]";
	}
}