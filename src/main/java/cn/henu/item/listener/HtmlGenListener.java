package cn.henu.item.listener;

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

import cn.henu.item.pojo.Item;
import cn.henu.pojo.TbItem;
import cn.henu.pojo.TbItemDesc;
import cn.henu.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * 监听商品添加消息，生成对应的静态页面
 * @author syw
 *
 */
public class HtmlGenListener implements MessageListener {
	@Autowired
	private ItemService itemService;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@Value("${HTML_GEN_PATH}")
	private String HTML_GEN_PATH;
	@Override
	public void onMessage(Message message) {
		//创建一个模板,参考jsp进行创建
		
		try {
			// 从消息中取出商品id
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			Long itemId = new Long(text);
			//等待事务提交
			Thread.sleep(1000);
			//根据商品id查询商品基本信息和详细信息
			TbItem tbItem = itemService.getItemById(itemId);
			Item item = new Item(tbItem);
			//取商品描述
			TbItemDesc itemDesc = itemService.selectTbItemDesc(itemId);
			//创建一个数据集把商品封装到里面
			Map map = new HashMap();
			map.put("item", item);
			map.put("itemDesc", itemDesc);
			//加载模板对象
			Configuration configuration = freeMarkerConfigurer.getConfiguration();
			Template template = configuration.getTemplate("item.ftl");
			//创建一个输出流,指定输出的目录及文件名
			Writer writer = new FileWriter(HTML_GEN_PATH+itemId+".html");
			//生成静态页面
			template.process(map, writer);
			//关闭流
			writer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
