package naturia.orders.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naturia.item.entity.Item;
import naturia.item.repository.ItemRepository;
import naturia.orders.entity.Orders;
import naturia.orders.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ItemRepository itemRepository;
	
	public Orders findOrderId(int OrderId) {
		return orderRepository.findById(OrderId).get();
	}
	
	public List<Orders> findOrdersBySellId(int sellId){
		return orderRepository.findAllBySellId(sellId);
	}
	
	public void registOrder(Orders order) {
		orderRepository.save(order);
	}
	
	public void deleteOrder(Orders order) {
		orderRepository.delete(order);
	}
	
	public void deleteAllOrder(List<Orders> order) {
		for (Orders orders : order) {
			Item item = orders.getItem();
			item.setItemStock(item.getItemStock() + orders.getOrderCount());
			item.setItemCount(item.getItemCount() - orders.getOrderCount());
			itemRepository.save(item);
			orderRepository.delete(orders);
		}
	}
	
	public List<Orders> findItemId(int itemId) {
		return orderRepository.findAllByItemId(itemId);
	}
}
