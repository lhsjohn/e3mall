package cn.e3mall.fast;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

public class FastDfsTest {

	@Test
	public void testUpload()throws Exception{
		//创建一个配置文件。内容就是tracker服务器地址
		//使用全局对象加载配置文件
		ClientGlobal.init("F:/Study/hgyWorkSpace/e3-manager-web/src/main/resources/conf/client.conf");
		//创建一个TrackerClient对象
		TrackerClient trackerClient=new TrackerClient();
		//通过TrackerClient获取一个TrackerServer对象
		TrackerServer trackerServer=trackerClient.getConnection();
		//创建一个StorageServer的引用，可以是null
		StorageServer storageServer=null;
	    //创建一个StorageClient,参数需要TrackerServer和StorageServer
		StorageClient storageClient=new StorageClient(trackerServer,storageServer);
		//使用StorageClient上传文件
		String[] strings = storageClient.upload_file("F:/Hexo/source/images/favicon.png","png", null);
		for (String string : strings) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testFastDfsClient() throws Exception{
		FastDFSClient fastDFSClient=new FastDFSClient("F:/Study/hgyWorkSpace/e3-manager-web/src/main/resources/conf/client.conf");
		String string = fastDFSClient.uploadFile("F:/Hexo/source/images/2018-08-06-2.jpg");
	    System.out.println(string);
	
	}
	
}
