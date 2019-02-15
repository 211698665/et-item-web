package cn.henu.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.henu.item.pojo.Item;
import cn.henu.pojo.TbItem;
import cn.henu.pojo.TbItemDesc;
import cn.henu.service.ItemService;

/**
 * 商品详情页面展示controller
 * @author syw
 *
 */
@Controller
public class ItemController {                       

	@Autowired
	private ItemService itemService;
	@RequestMapping("/item/{itemId}")//注意下面的@PathVariable里面的变量的名字要和这里的一致为itemId
	public String showItemInfo(@PathVariable Long itemId,Model model) {
		//1。调用服务查询商品基本信息
		TbItem tbItem = itemService.getItemById(itemId);
		Item item = new Item(tbItem);
		//2.取商品描述信息
		TbItemDesc itemDesc = itemService.selectTbItemDesc(itemId);
		//3.把信息传递给页面
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", itemDesc);
		//4.返回逻辑视图
		return "item";
	}
}
