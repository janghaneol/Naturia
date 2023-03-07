package naturia.orders.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import naturia.item.entity.Item;
import naturia.orders.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{
	List<Orders> findAllByItemId(int itemId);
	
	List<Orders> findAllBySellId(int sellId);
}
