package cn.e3mall.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerTest {
    
	@Test
	 public void testFreeMarker() throws Exception{
		 //1.创建一个模板文件
		 //2.创建一个Configuration对象
		 Configuration configuration=new Configuration(Configuration.getVersion());
		 //3.设置模板文件保存的目录
		 configuration.setDirectoryForTemplateLoading(new File("F:/Study/hgyWorkSpace/e3-item-web/src/main/webapp/WEB-INF/ftl"));
		 //4.模板文件的编码格式，一般是UTF-8
		 configuration.setDefaultEncoding("utf-8");
		 //5.加载一个模板文件，创建一个模板对象
		 Template template = configuration.getTemplate("student.ftl");
		 //6.创建一个数据集，可以是pojo,也可以是Map,推荐使用Map
		 Map data=new HashMap<>();
		 data.put("hello", "hello freemarker!");
		 //创建一个pojo对象
		 Student student=new Student(1,"小明",18,"嘉祥");
		 data.put("student", student);
		 
		 //添加一个list
		 List<Student>stuList=new ArrayList<>();
		 stuList.add(new Student(1,"小明1",18,"济宁"));
		 stuList.add(new Student(2,"小明2",18,"济宁"));
		 stuList.add(new Student(3,"小明3",18,"济宁"));
		 stuList.add(new Student(4,"小明4",18,"济宁"));
		 stuList.add(new Student(5,"小明5",18,"济宁"));
		 stuList.add(new Student(6,"小明6",18,"济宁"));
		 stuList.add(new Student(7,"小明7",18,"济宁"));
		 data.put("stuList", stuList);
		 data.put("date", new Date());
		 //null值的测试
		 data.put("val",null );
		 //7.创建一个Writer对象，指定输出文件的路径以及文件名
		 Writer out=new FileWriter(new File("C:/Users/lihuashuo/Desktop/freemarker/student.html"));
		 //8.生成静态页面
		 template.process(data, out);
		 //9.关闭流
		 out.close();
		 
		 
		 
		 
	 }
}
