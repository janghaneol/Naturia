package naturia.sell.cotroller;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import naturia.item.entity.Item;
import naturia.item.service.ItemService;
import naturia.orders.entity.Orders;
import naturia.orders.service.OrderService;
import naturia.sell.entity.Sell;
import naturia.sell.service.SellService;

@Controller
@Slf4j
@RequestMapping("/sale")
public class SellController {
	@Autowired
	SellService sellService;
	@Autowired
	ItemService itemService;
	@Autowired
	OrderService orderService;
	
	@GetMapping
	public String sellRegistView(Sell sell,Model model) {
		return "sellRegist";
	}
	
	@PostMapping
	public String sellRegist(Sell sell,Model model, RedirectAttributes attributes) {
		Sell regSell = new Sell();
		regSell.setManager(sell.getManager());
		regSell.setStore(sell.getStore());
		regSell.setSellDate(LocalDateTime.now());
		Integer sellId = sellService.sellRegist(regSell);
		attributes.addAttribute("sellId", sellId);
		return "redirect:/sale/{sellId}";
	}
	
	@GetMapping("/list")
	public String sellListView(Sell sell,Model model) {
		model.addAttribute("sell", sellService.sellAllView());
		return "sellList";
	}
	
	@GetMapping("/list/{sellId}")
	public String sellPageView(@PathVariable Integer sellId, Model model) {
		model.addAttribute("sell", sellService.findSell(sellId));
		model.addAttribute("order", orderService.findOrdersBySellId(sellId));
		return "sellDetail";
	}
	
	@GetMapping("/{sellId}")
	public String sellOrderView(@PathVariable Integer sellId, Model model) {
		List<Item> item = itemService.allItemView();
		List<Orders> order = orderService.findOrdersBySellId(sellId);
		Sell findSell = sellService.findSell(sellId);
		model.addAttribute("order", order);
		model.addAttribute("sell", findSell);
		model.addAttribute("item", item);
		return "orderRegist";
	}
	
	@PostMapping("/{sellId}")
	public String orderRegist(@PathVariable Integer sellId, Model model, @RequestParam String itemName, RedirectAttributes attributes,
			@RequestParam(required = false) Integer orderCount) {
		Item item = itemService.findItemName(itemName);
		if(item == null || orderCount == null) {
			return "redirect:/sale/{sellId}";
		}
		
		Orders order = new Orders();
		order.setItemId(item.getItemId());
		order.setSellId(sellId);
		order.setOrderCount(orderCount);
		orderService.registOrder(order);
		item.setItemCount(item.getItemCount() + orderCount);
		item.setItemStock(item.getItemStock() - orderCount);
		itemService.saveItem(item);
		return "redirect:/sale/{sellId}";
	}
	
	@GetMapping("/{sellId}/delete")
	public String sellDelete(@PathVariable Integer sellId,Model model) {
		Sell sell = sellService.findSell(sellId);
		List<Orders> orders = orderService.findOrdersBySellId(sell.getSellId());
		orderService.deleteAllOrder(orders);
		sellService.sellDelete(sellId);
		return "redirect:/sale/list";
	}
	
	@GetMapping("/{sellId}/{orderId}")
	public String orderDelete(@PathVariable Integer sellId, @PathVariable Integer orderId, Model model,HttpServletRequest request,@RequestParam(name = "redirect" , defaultValue = "/")String redirect,
			HttpSession session) {
		Orders order = orderService.findOrderId(orderId);
		Item item = order.getItem();
		item.setItemStock(item.getItemStock() + order.getOrderCount());
		item.setItemCount(item.getItemCount() - order.getOrderCount());
		itemService.saveItem(item);
		orderService.deleteOrder(order);
		
		String uri = request.getHeader("Referer");
		request.getSession().setAttribute("prevPage", uri);
		redirect=(String)session.getAttribute("prevPage");
		return "redirect:"+redirect;
	}
	
	
}
