package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;
import cn.e3mall.pojo.TbContentCategoryExample.Criterion;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbItem;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public List<EasyUITreeNode> getContentCatList(long parentId) {
		//根据ParentId查询子节点列表
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria=example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> catList = contentCategoryMapper.selectByExample(example);
		//转换成EasyUITreeNode的形式
		List<EasyUITreeNode> nodeList=new ArrayList<>();
		for (TbContentCategory contentCategory : catList) {
			EasyUITreeNode node=new EasyUITreeNode();
			node.setId(contentCategory.getId());
			node.setText(contentCategory.getName());
			node.setState(contentCategory.getIsParent()?"closed":"open");
		    nodeList.add(node);
		}
		
		   return nodeList;
	}


	@Override
	public E3Result addContentCategory(long parentId, String name) {
		//创建一个tb_content_category对应的pojo
		TbContentCategory contentCategory=new TbContentCategory();
		//设置pojo的属性
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		//1.正常 2.删除
		contentCategory.setStatus(1);
		//默认排序都是1
		contentCategory.setSortOrder(1);
		//新添加的结果肯定是叶子节点
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//插入到数据库中
		contentCategoryMapper.insert(contentCategory);
		//判断父节点的isparent属性，如果不是true,改为true
		//根据parentid查询父节点
		TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		
		if(!parent.getIsParent()) {
			//返回结果,返回E3Result包含pojo
			parent.setIsParent(true);
		//更新到数据库中
		contentCategoryMapper.updateByPrimaryKey(parent);
		}

	     return E3Result.ok(contentCategory);}


	@Override
	public E3Result deleteContentCategory(long id) {
		TbContentCategory contentCategory=contentCategoryMapper.selectByPrimaryKey(id);
		long parentId=contentCategory.getParentId();
		contentCategoryMapper.deleteByPrimaryKey(id);
		TbContentCategory parent=contentCategoryMapper.selectByPrimaryKey(parentId);
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		long count = contentCategoryMapper.countByExample(example);
		if(count<1) {
			parent.setIsParent(false);
		}else {
			parent.setIsParent(true);
		}
		contentCategoryMapper.updateByPrimaryKey(parent);
		return E3Result.ok();
	}


	@Override
	public E3Result updateContentCategory(long id, String name) {
		TbContentCategory contentCategory=contentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
		return E3Result.ok();
	}






}
