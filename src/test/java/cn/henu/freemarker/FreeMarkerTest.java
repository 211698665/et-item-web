package cn.henu.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerTest {

	@Test
	public void testFreeMarker()throws Exception {
		//1.创建一个模板文件,这里实在web-INF里面创建一个ftl文件夹，里面有hello.ftl
		//2.创建一个Configuration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		//3.设置模板文件的保存目录
		configuration.setDirectoryForTemplateLoading(new File("C:\\javaspace\\et-item-web\\src\\main\\webapp\\WEB-INF\\ftl"));
		//4.设置模板文件的编码格式
		configuration.setDefaultEncoding("utf-8");
		//5.加载一个模板文件，创建一个模板对象
		Template template = configuration.getTemplate("student.ftl");
		//6.创建一个数据集,可以是pojo,可以使map(推荐使用map)
		Map<Object,Object> map = new HashMap<>();
		map.put("hello", "hello freemarker syw");
		//添加一个pojo对象
		Student student = new Student(1,"xiaom",18,"huihasdf");
		map.put("student", student);
		//添加一个list
		List<Student> list=new LinkedList();
		list.add(new Student(2,"xiaohua",23,"safa"));
		list.add(new Student(3,"3xiaohua",24,"s3afa"));
		list.add(new Student(4,"xiaohua",25,"s4afa"));
		list.add(new Student(5,"xiaohua",26,"sf1fsdfa"));
		map.put("slist", list);
		//添加日期类型
		map.put("date", new Date());
		//空值的测试
		map.put("val", null);
		//7.创建一个writer对象，指定输出文件的路径和文件名
		Writer writer = new FileWriter(new File("C:\\javafreemarker\\student.html"));
		//8.生成静态页面
		template.process(map, writer);
		//9.关闭流
		writer.close();
	}
}
