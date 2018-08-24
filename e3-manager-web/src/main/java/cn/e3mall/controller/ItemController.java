package cn.e3mall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;

@Controller
public class ItemController {
    
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem ibItem=itemService.getItemById(itemId);
	   return ibItem;
	}
	
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows) {
		//查询商品列表
	  EasyUIDataGridResult result=itemService.getItemList(page, rows);
	  return result;
	}
	
	/**
	 * 商品添加功能
	 */
	
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addItem(TbItem item,String desc) {
		E3Result result=itemService.addItem(item, desc);
		return result;
	}
	
	/**
	 * 商品编辑功能 描述信息回显
	 */
    @RequestMapping(value="/rest/item/query/item/desc/{itemId}")
    @ResponseBody
    public E3Result getItemDescById(@PathVariable Long itemId) {
    	TbItemDesc result=itemService.getItemDescById(itemId);
    	System.out.println(E3Result.ok(result.getItemDesc()));
    	return E3Result.ok(result);
    }
	
	/**
	 * 商品删除
	 */
    @RequestMapping(value="/rest/item/delete",method=RequestMethod.POST)
    @ResponseBody
    public E3Result idsItemDel(@RequestParam("ids")String ids) {
    	String[] split=ids.split(",");
    	List<Long> del_ids=new ArrayList<>();
    	for (String string : split) {
    		del_ids.add(Long.parseLong(string));
		}
    	E3Result result = itemService.deleteItemsByIds(del_ids);
    	return result;
    			
    }
    
	
	
	
}
