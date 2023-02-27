package naturia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import naturia.item.entity.Item;
import naturia.item.service.ItemService;

@Controller
@Slf4j
public class HomeController {
	@Autowired
	ItemService itemService;
	
	@GetMapping("/")
	public String indexView(Model model) {
		List<Item> items = itemService.allItemView();
		model.addAttribute("item", items);
		log.info("itemList : : : ", items);
		return "index";
	}
}
