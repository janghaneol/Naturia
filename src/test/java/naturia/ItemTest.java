package naturia;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import naturia.item.entity.Item;
import naturia.item.service.ItemService;

@SpringBootTest
@Slf4j
public class ItemTest {
	@Autowired
	ItemService itemService;
	
	@Test
	public void itemRegi() {
		Item item = new Item();
		item.setItemName("엑스트라 스트레이트");
		item.setItemStock(2431);
		item.setItemCount(0);
		itemService.saveItem(item);
	}
}
