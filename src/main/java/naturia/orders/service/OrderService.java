package naturia.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naturia.orders.entity.Orders;
import naturia.orders.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	public List<Orders> findOrdersBySellId(int sellId){
		return orderRepository.findAllBySellId(sellId);
	}
}
