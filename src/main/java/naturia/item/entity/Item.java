package naturia.item.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
@DynamicInsert
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq_gen")
	@Column(name = "item_id")
	private Integer itemId;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_stock")
	private Integer itemStock;
	
	@Column(name = "item_count")
	private Integer itemCount;
	
	@Column(name = "img_cont_type")
	private String imgContType;
	
	@Column(name = "img_file_name")
	private String imgFileName;
	
	@Column
	private LocalDateTime regDate;
}
