package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;

public interface ItemService {

	TbItem getItemById(long itemid);
	EasyUIDataGridResult getItemList(int page,int rows);
    E3Result addItem(TbItem item,String desc);
    TbItemDesc getItemDescById(Long itemId);
    E3Result deleteItemsByIds(List<Long>ids);
    TbItemDesc getItemDescById(long itemId);
}
