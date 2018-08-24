package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
/**
 * 内容服务
 * @author lihuashuo
 *
 */

@Controller
public class ContentCatController {

 @Autowired
 private ContentCategoryService contentCategoryService;
  
  @RequestMapping("/content/category/list")
  @ResponseBody
  public List<EasyUITreeNode> getContentCatList(
		  @RequestParam(name="id",defaultValue="0") Long parentId) {
	  List<EasyUITreeNode> catList = contentCategoryService.getContentCatList(parentId);
	  return catList;
  }
  
  /**
   * 添加分类节点
   */
  
  @RequestMapping(value="/content/category/create",method=RequestMethod.POST)
  @ResponseBody
  public E3Result createContentCategory(Long parentId,String name) {
	  //调用服务添加节点
	  E3Result result = contentCategoryService.addContentCategory(parentId, name);
	  return result;
  }
  
  /**
   * 删除分类节点
   */
  @RequestMapping(value="/content/category/delete/",method=RequestMethod.POST)
  @ResponseBody
  public E3Result deleteContentCategory(Long id) {
	  E3Result result = contentCategoryService.deleteContentCategory(id);
	  return result;
  }
  
  
  /**
   * 更新分类节点
   */
  @RequestMapping(value="/content/category/update",method=RequestMethod.POST)
  @ResponseBody
  public E3Result updateContentCategory(Long id,String name) {
	  E3Result result = contentCategoryService.updateContentCategory(id, name);
	  return result;
  }
  

  
  
  
  
  
  
}
