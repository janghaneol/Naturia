package naturia.item.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@SequenceGenerator(name = "item_seq_gen", sequenceName = "item_seq", allocationSize = 1)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq_gen")
	@Column(name = "item_id")
	private int itemId;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_stock")
	private int itemStock;
	
	@Column(name = "item_count")
	private int itemCount;
	
	@Column(name = "img_cont_type")
	private String imgContType;
	
	@Column(name = "img_file_name")
	private String imgFileName;
	
	@Column
	private LocalDateTime regDate;
}
