package naturia.orders.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import naturia.item.entity.Item;
import naturia.sell.entity.Sell;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@SequenceGenerator(
		name = "orders_seq_gen",
		sequenceName = "orders_seq",
		initialValue = 1,
		allocationSize = 1)
public class Orders {
	@Id
	@Column(name ="order_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq_gen")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="item_id",insertable = false, updatable = false)
	private Item item;
	
	@Column(name="item_id")
	private Integer itemId;
	
	@Column(name="sell_id")
	private Integer sellId;
	
	@Column(name="order_count")
	private Integer orderCount;
	
	@Column(name = "order_date")
	private LocalDateTime orderDate;
	
	@ManyToOne
	@JoinColumn(name="sell_id",insertable = false, updatable = false)
	private Sell sell;
}
