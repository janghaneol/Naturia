package naturia;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import naturia.item.entity.Item;
import naturia.item.service.ItemService;
import naturia.orders.entity.Orders;
import naturia.orders.repository.OrderRepository;
import naturia.orders.service.OrderService;
import naturia.sell.entity.Sell;
import naturia.sell.service.SellService;

@SpringBootTest
@Slf4j
public class SellTest {
	@Autowired
	SellService sellService;
	@Autowired
	ItemService itemService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;
	
	@Test
//	@Disabled
	public void SellRegi() {
		List<Item> items = itemService.allItemView();
		for (Item item : items) {
			log.info("물품 정보 : : : : {}",item);
		}
	}
	
	@Test
	@Disabled
	public void OrderList() {
		int a = 8;
		List<Orders> order = orderService.findOrdersBySellId(a);
		for (Orders order2 : order) {
			log.info("주문 정보 : : : : : {}",order2);
		}
		
	}
}
