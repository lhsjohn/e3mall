package cn.e3mall.item.listener;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.e3mall.item.pojo.Item;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * 监听商品添加消息,生成对应的静态页面
 * @author lihuashuo
 *
 */
public class HtmlGenListener implements MessageListener {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private FreeMarkerConfigurer freemarkerConfigure;
	
	@Value("${HTML_GEN_PATH}")
	private String HTML_GEN_PATH;
	
	@Override
	public void onMessage(Message message) {
		try {
			//创建一个模板,参考jsp创建
			TextMessage textMessage=(TextMessage) message;
			//从消息中取商品id
			String text = textMessage.getText();
			Long itemId=new Long(text);
			//等待事务提交
			Thread.sleep(1000);	
			//根据商品id来查询商品信息，商品基本信息和商品描述
			TbItem tbItem = itemService.getItemById(itemId);
			Item item=new Item(tbItem);
			//取商品描述
			TbItemDesc itemDesc = itemService.getItemDescById(itemId);
		    //创建一个数据集,把商品数据封装进去
			Map data=new HashMap<>();
			data.put("item", item);
			data.put("itemDesc", itemDesc);
			//加载模板对象
			Configuration configuration = freemarkerConfigure.getConfiguration();
			Template template = configuration.getTemplate("item.ftl");	
			//创建一个输出流，指定输出的目录以及文件名
			Writer out=new FileWriter(new File(HTML_GEN_PATH+"item/"+itemId+".html"));
			//生成静态页面
			template.process(data, out);
			//关闭流
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}
