package naturia.sell.cotroller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import naturia.sell.entity.Sell;
import naturia.sell.service.SellService;

@Controller
@Slf4j
@RequestMapping("/sale")
public class SellController {
	@Autowired
	SellService sellService;
	
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
	
	@GetMapping("/{sellId}")
	public String sellOrderView(@PathVariable Integer sellId, Model model) {
		Sell findSell = sellService.findSell(sellId);
		model.addAttribute("sell", findSell);
		return "sellRegist";
	}
}
