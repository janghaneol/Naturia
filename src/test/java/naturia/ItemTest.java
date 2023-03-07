package naturia;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import naturia.item.entity.Item;
import naturia.item.service.ItemService;
import naturia.orders.entity.Orders;
import naturia.orders.service.OrderService;
import naturia.sell.entity.Sell;
import naturia.sell.service.SellService;

@SpringBootTest
@Slf4j
public class ItemTest {
	@Autowired
	ItemService itemService;
	@Autowired
	OrderService orderService;
	@Autowired
	SellService sellService;

	@Test
	@Disabled
	public void itemRegi() {
		Item item = new Item();
		item.setItemName("엑스트라 스트레이트");
		item.setItemStock(2431);
		item.setItemCount(0);
		itemService.saveItem(item);
	}

	@Test
	@Disabled
	public void sellList() {
		List<Orders> orders = orderService.findOrdersBySellId(8);
		for (Orders order : orders) {
			log.info("OrderItemList : : : : :{} ", order.getItem().getItemName());
		}
	}

	@Test
	public void sellRegi() {
		Sell sell = sellService.findSell(38);
		List<Item> item = itemService.allItemView();

		for (Item item2 : item) {
			Orders order = new Orders();
			order.setSellId(sell.getSellId());
			order.setItemId(item2.getItemId());
			orderService.registOrder(order);
		}
	}
	
}
