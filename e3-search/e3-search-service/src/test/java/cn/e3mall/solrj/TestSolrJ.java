package cn.e3mall.solrj;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolrJ {

	@Test
	public void addDocument() throws Exception {
		// 创建一个SolrServer对象,创建一个连接，参数solr服务的url
		SolrServer solrServer = new HttpSolrServer("http://192.168.1.128:8080/solr/collection1");
		// 创建一个文档对象SolrInputDocument
		SolrInputDocument document = new SolrInputDocument();
		// 向文档对象中添加域。文档中必须包含一个id域，所有的域的名称必须在schema.xml中定义
		document.addField("id", "doc01");
		document.addField("item_title", "测试商品01");
		document.addField("item_price", 1000);

		// 把文档写入索引库
		solrServer.add(document);
		// 提交
		solrServer.commit();
	}

	@Test
	public void deleteDocument() throws Exception {
		// 创建一个SolrServer对象,创建一个连接，参数solr服务的url
		SolrServer solrServer = new HttpSolrServer("http://192.168.1.128:8080/solr/collection1");
		// 删除文档
		// solrServer.deleteById("doc01");
		solrServer.deleteByQuery("id:doc01");
		// 提交
		solrServer.commit();
	}

	@Test
	public void queryIndex() throws Exception {
		// 创建一个SolrServer对象
		SolrServer solrServer = new HttpSolrServer("http://192.168.1.128:8080/solr/collection1");
		// 创建一个SolrQuery对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		// query.setQuery("*:*");
		// 查询所有
		query.set("q", "*:*");
		// 执行查询，QueryResponse对象
		QueryResponse response = solrServer.query(query);
		// 取文档列表。取查询结果的总记录数
		SolrDocumentList solrDocumentList = response.getResults();
		System.out.println("查询结果总记录数:" + solrDocumentList.getNumFound());
		// 遍历文档列表，从文档中取域的内容
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}
	}
 
	@Test
	public void queryIndexFuza() throws Exception {
		// 创建一个SolrServer对象
		SolrServer solrServer = new HttpSolrServer("http://192.168.1.128:8080/solr/collection1");
		// 创建一个SolrQuery对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery("手机");
		query.setStart(0);
		query.setRows(20);
		query.set("df", "item_title");
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");

		// 执行查询，QueryResponse对象
		QueryResponse response = solrServer.query(query);
		// 取文档列表。取查询结果的总记录数
		SolrDocumentList solrDocumentList = response.getResults();
		System.out.println("查询结果总记录数:" + solrDocumentList.getNumFound());
		// 遍历文档列表，从文档中取域的内容
		//取高亮显示
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title="";
			if(list!=null && list.size()>0) {
				title = list.get(0);
			} else {
				title=(String) solrDocument.get("item_title");
			}
			
			System.out.println(title);
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}
	}

}
