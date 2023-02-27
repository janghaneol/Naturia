package naturia.sell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naturia.sell.entity.Sell;
import naturia.sell.repository.SellRepository;

@Service
@Transactional
public class SellService {
	@Autowired
	SellRepository sellRepository;
	
	public Integer sellRegist(Sell sell) {
		sellRepository.save(sell);
		return sell.getSellId();
	}
	
	public Sell findSell(int sellId) {
		return sellRepository.findById(sellId).get();
	}
	
	public List<Sell> sellAllView(){
		return sellRepository.findAll();
	}
	
	
}
