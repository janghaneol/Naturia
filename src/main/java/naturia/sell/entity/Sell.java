package naturia.sell.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import naturia.item.entity.Item;

@Entity
@Data
@SequenceGenerator(name = "sell_seq_gen", sequenceName = "sell_seq", allocationSize = 1)
@AllArgsConstructor
@RequiredArgsConstructor
public class Sell {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sell_seq_gen")
	@Column(name = "sell_id")
	private Integer sellId;
	
	@Column(name = "store")
	private String store;
	
	@Column
	private String manager;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sell_date")
	private LocalDateTime sellDate;
}
