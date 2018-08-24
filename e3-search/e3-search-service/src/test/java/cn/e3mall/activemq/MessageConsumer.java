package cn.e3mall.activemq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageConsumer {
    //初始化Spring容器
	//等待
	  
	@Test
	public void msgConsumer() throws Exception{
		//初始化Spring容器
	ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");	
		System.in.read();
		
	}
}
