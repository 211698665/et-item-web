package cn.henu.item.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 生成静态页面测试
 * @author syw
 *
 */
@Controller
public class HtmlGenController {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@RequestMapping("/testfreemarker")
	@ResponseBody
	public String GingtaiHtml() throws Exception{
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		//加载模板
		Template template = configuration.getTemplate("hello.ftl");
		//创建数据集
		Map date=new HashMap();
		date.put("hello", 123456);
		//指定文件输出的路径和文件名
		Writer writer = new FileWriter(new File("C:\\javafreemarker\\hello.html"));
		//输出文件
		template.process(date, writer);
		//关闭流
		writer.close();
		return "OK";
	}
	
}
