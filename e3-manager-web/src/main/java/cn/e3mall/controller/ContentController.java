package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

/**
 * 内容管理Controler
 * 
 * @author lihuashuo
 *
 */
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping(value = "/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(@RequestParam(value = "categoryId", defaultValue = "0") long categoryId,
			Integer page, Integer rows) {
		EasyUIDataGridResult result = contentService.getContentCatDataList(categoryId, page, rows);
		return result;
	}

	@RequestMapping(value = "/content/save", method = RequestMethod.POST)
	@ResponseBody
	public E3Result addContent(TbContent content) {
		// 调用服务把内容数据保存到数据库
		E3Result result = contentService.addContent(content);
		return result;
	}

}
