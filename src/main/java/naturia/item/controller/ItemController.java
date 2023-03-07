package naturia.item.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import naturia.item.entity.Item;
import naturia.item.service.ItemService;
import naturia.orders.entity.Orders;
import naturia.orders.service.OrderService;

@Controller
@Slf4j
@RequestMapping("/item")
public class ItemController {
	@Autowired
	ItemService itemService;
	@Autowired
	OrderService orderService;
	
	
	@GetMapping
	public String itemsView(Model model) {
		List<Item> items = itemService.allItemView();
		model.addAttribute("items", items);
		log.info("items : : : {}", items);
		return "item";
	}
	
	@GetMapping("/regist")
	public String itemRegView(Model model) {
		Item item = new Item();
		model.addAttribute("item", item);
		return "itemRegist";
	}
	
	@PostMapping("/regist")
	public String itemReg(Model model, Item item) {
		Item regItem = new Item();
		regItem.setItemName(item.getItemName());
		regItem.setItemStock(item.getItemStock());
		regItem.setItemCount(item.getItemCount());
		regItem.setRegDate(LocalDateTime.now());
		itemService.saveItem(regItem);
		return "redirect:/item";
	}
	@GetMapping("/{itemId}")
	public String itemView(@PathVariable int itemId,Model model) {
		Item item = itemService.itemView(itemId);
		List<Orders> orderItem = orderService.findItemId(itemId);
		model.addAttribute("item", item);
		model.addAttribute("order", orderItem);
		return "itemModify";
	}
	
	@PostMapping("/{itemId}")
	public String itemMod(@PathVariable int itemId, Model model, Item item) {
		itemService.itemModify(item, itemId);
		return "redirect:/item";
	}
}
