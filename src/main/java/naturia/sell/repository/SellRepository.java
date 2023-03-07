package naturia.sell.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import naturia.sell.entity.Sell;

public interface SellRepository extends JpaRepository<Sell, Integer>{

	List<Sell> findAllByOrderBySellDateDesc();
}
