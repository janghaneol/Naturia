package naturia.item.service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naturia.item.entity.Item;
import naturia.item.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	ItemRepository itemRepository;
	
	// 제품 등록
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	public Item itemView(Integer id) {
		return itemRepository.findById(id).get();
	}
	
	public List<Item> allItemView(){
		return itemRepository.findAll();
	}
	
	public Item findItemName(String itemName) {
		return itemRepository.findAllByItemName(itemName);
	}
	
	public void itemModify(Item item, int id) {
		Item updateItem = itemRepository.findById(id).get();
		updateItem.setItemName(item.getItemName());
		updateItem.setImgFileName(item.getImgFileName());
		updateItem.setRegDate(LocalDateTime.now());
		updateItem.setItemStock(item.getItemStock());
		itemRepository.save(updateItem);
	}
	
	public void deleteItem(int itemId) {
		Item item = itemRepository.findById(itemId).get();
		itemRepository.delete(item);
	}
}
