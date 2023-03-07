package naturia.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import naturia.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	public Item findAllByItemName(String itemName);
}
